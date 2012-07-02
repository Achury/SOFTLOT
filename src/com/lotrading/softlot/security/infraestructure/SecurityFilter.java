package com.lotrading.softlot.security.infraestructure;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.security.logic.IRoleLogic;

/**
 * Implements the Filter Interface
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class SecurityFilter implements Filter{

	private Log log = LogFactory.getLog(SecurityFilter.class);
	
	private javax.servlet.FilterConfig _filterConfig;
	private IEmployeeLogic employeeLogic;
	private IResourceLogic resourceLogic;
	private IRoleLogic roleLogic;
	private java.util.ResourceBundle resource;
	private java.util.Map urlExceptions;
	
	public SecurityFilter(){

	}
	
	public void init(FilterConfig arg0) throws ServletException {
		_filterConfig = arg0;
	}
	
	public void destroy() {
		_filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		boolean ignore = false;                   
        boolean authenticated = false;
        boolean authorized = false;
        
        Employee employee = null;
        
        HttpServletRequest srequest = (HttpServletRequest)request;
        HttpServletResponse sresponse = (HttpServletResponse)response;
        
        /* obtains the requested resource */            
        StringBuffer resourceRequested = new StringBuffer();
        try {
        	if (resource == null ) {
        		resource = ResourceBundle.getBundle("softlot");        
            }
        	
            resourceRequested.append(srequest.getPathInfo());
            
            boolean tmpExcent = isExcent(resourceRequested.toString());
           
            /* verifies if the resource is excent */
            if ( resourceRequested.toString() == null || 
            		resourceRequested.toString().equals("null") ||
            		tmpExcent ) {
                 
                ignore = true;
                return;
            }
        	
        	if (srequest.getSession(false) != null) {
				if (srequest.getSession(false).getAttribute("#{employee_auth}") != null) {
					employee = (Employee) srequest.getSession(false)
							.getAttribute("#{employee_auth}");
				}
			}
        	
			if(employee != null){
            	authenticated = true;
            }else{
            	authenticated = false;
            	return;
            }
            
            //TODO: Validate if the user is authorized to access the resource
            String url = resourceRequested.toString();
            authorized = isAuthorized(url, employee);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally  {
            if ( ignore ) {
                chain.doFilter(request, response);
            } 
            else {
                if ( ! authenticated ) {
                    sresponse.sendRedirect(srequest.getContextPath()+"/faces/jsp/login/noAuthenticated.jsp");
                }
                else if ( ! authorized ) {
                    sresponse.sendRedirect(srequest.getContextPath()+"/faces/jsp/login/noAuthorized.jsp");
                }
                else {
                    chain.doFilter(request, response);
                }
            }
        }   
	}
	
	private boolean isExcent(String url) throws Exception {
		//TODO: implements this method
		boolean _tmpRetorno = false;
        
        // carga la lista de exlcusiones
		if (urlExceptions == null) {
			urlExceptions = new HashMap();
			String _tmpExcepciones = resource.getString("exclusions.filter");
			// TODO clear memory (done)
			StringTokenizer _TmpStok = new StringTokenizer(_tmpExcepciones, ",");
			while (_TmpStok.hasMoreTokens()) {
				String _tmpToken = _TmpStok.nextToken();
				urlExceptions.put(_tmpToken, resource.getString("exclude." + _tmpToken + ".expression"));
				log.info("Loading security filter exclusion [" + _tmpToken
						+ ": " + resource.getString("exclude." + _tmpToken + ".expression") + "]");
				_tmpToken = null;
			}
			_TmpStok = null;
		}

        // valida la URL contra las excepciones del filtros
        if (urlExceptions != null) {         
            Pattern _tmpPattern;
            Matcher _tmpMatcher;
            Iterator _tmpIter = urlExceptions.values().iterator();
            String _tmpFilter = "";
            while (_tmpIter.hasNext()) {
                _tmpFilter = (String)_tmpIter.next();
                _tmpPattern = Pattern.compile(_tmpFilter);
                _tmpMatcher = _tmpPattern.matcher(url);
                if (_tmpMatcher.find()) {
					log.info("[" + url	+ "] resource excluded from authorization filter. Rule [" + _tmpFilter + "]");
                    _tmpRetorno = true;
                    break;
                }
            }
            
            _tmpPattern = null;
            _tmpMatcher = null;
        }
        
        return _tmpRetorno;
	}
	
	private boolean isAuthorized(String url, Employee employee) throws Exception{
		boolean _returnTmp = false;
		ResourceRole resourceRole = new ResourceRole();
		
		Resource resource = new Resource();
		resource.setUrl(url);
		
		//Filtrar recursos por roles
		employee.setRoles(employeeLogic.loadEmployeeRoles(true, employee));
		Iterator rolesIt = employee.getRoles().iterator();
		
		while(rolesIt.hasNext()){
			Role _roleTmp = (Role)rolesIt.next();
			
			List resources = roleLogic.loadRoleResources(true, _roleTmp);
			Iterator resIt = resources.iterator();
			
			while(resIt.hasNext()){
				Resource _resourceTmp = (Resource)resIt.next();
				
				if(_resourceTmp.getUrl().equals(url)){
					resource = _resourceTmp;
					break;
				}
			}
		}
		
		if(resource.getResourceId() > 0){
			_returnTmp = true;
		}
		
		return _returnTmp;
	}
	
	public IEmployeeLogic getEmployeeLogic(){
		return employeeLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeLogic(IEmployeeLogic newVal){
		employeeLogic = newVal;
	}

	public javax.servlet.FilterConfig get_filterConfig(){
		return _filterConfig;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void set_filterConfig(javax.servlet.FilterConfig newVal){
		_filterConfig = newVal;
	}

	public java.util.ResourceBundle getResource(){
		return resource;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResource(java.util.ResourceBundle newVal){
		resource = newVal;
	}

	public java.util.Map getUrlExceptions(){
		return urlExceptions;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUrlExceptions(java.util.Map newVal){
		urlExceptions = newVal;
	}

	public void setResourceLogic(IResourceLogic resourceLogic) {
		this.resourceLogic = resourceLogic;
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	public void setRoleLogic(IRoleLogic roleLogic) {
		this.roleLogic = roleLogic;
	}

	public IRoleLogic getRoleLogic() {
		return roleLogic;
	}

}