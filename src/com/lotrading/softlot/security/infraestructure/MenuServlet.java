package com.lotrading.softlot.security.infraestructure;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.tools.codec.Base64Encoder;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.security.logic.IRoleLogic;

/**
 * Servlet encargado de procesar las peticiones que se realizan para las operaciones con citas.
 * 
 * @version 1.0
 * @author LandSoft S.A. <br>
 *         <b>Fecha de desarrollo</b>23/01/2009 <br>
 *         <a href='http://www.landsoft.com.co'>LandSoft S.A.</a>
 */
public class MenuServlet extends HttpServlet {

	private static final long serialVersionUID = 7200848925291498954L;
	
	private static Log log = LogFactory.getLog(MenuServlet.class);

	private static ApplicationContext appContext;
	private static BeanFactory beanFactory;
	private IRoleLogic roleLogic;
	private IResourceLogic resourceLogic;
	private IEmployeeLogic employeeLogic;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		setRoleLogic();
		setEmployeeLogic();
		setResourceLogic();
	}

	@SuppressWarnings("rawtypes")
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		String output = "";
		
		try{
			Employee employee = (Employee)request.getSession(false).getAttribute("#{employee_auth}");
			output = "<html> "+
								"<head> "+
									"<link rel='stylesheet' type='text/css' media='screen' href='../../css/softlot/jquery-ui-1.8.11.custom.css'/>"+
									"<link rel='stylesheet' type='text/css' media='screen' href='../../css/softlot.css'/>"+
									"<script type='text/javascript' src='../../js/jquery-1.4.2.min.js'></script>"+
									"<script type='text/javascript' src='../../js/jquery-ui-1.8.11.custom.js'></script>"+
									"<script type='text/javascript'>"+
										"jQuery(function(){ "+
											"jQuery('#accordion').accordion({autoHeight: false, "+
									            "navigation: true}); "+
										"}); "+
									"</script> "+
								"</head> "+
								"<body> "+
								"<div id='accordion' class='menuacordion' > ";
			
			//Filtrar recursos por roles
			employee.setRoles(employeeLogic.loadEmployeeRoles(true, employee));
			
			String contextoWeb = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			NavegationMenuHelper nhelper = new NavegationMenuHelper(contextoWeb);
			

			List _optionsTmp = employeeLogic.loadOptions(true, employee);
			if(_optionsTmp != null){
				Iterator _optionsIt = _optionsTmp.iterator();
				
				while(_optionsIt.hasNext()){
					Option _optionTmp = (Option)_optionsIt.next();
					
					nhelper.addOption(_optionTmp);
				}
			}
			
			output += 	getNavigatorItems(nhelper.items);
			
			output += 	"</div> "+
						"</body> "+
						"</html>";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Escritura de respuesta
		PrintWriter out = response.getWriter();
		out.print(output);
	}
	
	private String getNavigatorItems(List sublist) throws Exception {
		log.debug("getNavigatorItems(MenuNavegacionHelper)");
		// TODO clear memory (ignored)
		String output = "";
		List retorno = new ArrayList();
		if (sublist != null) {
			for (int i = 0; i < sublist.size(); i++) {
				Option _tmpOption = (Option) sublist.get(i);
				
				if (!_tmpOption.isParent()) {
					Resource _resTmp = resourceLogic.loadResource(_tmpOption.getResource());
					
					if(_resTmp != null){
						Base64Encoder encoder = new Base64Encoder(_resTmp.getUrl());
						String urlCodificada = encoder.processString();
						output += 	"<ul><li><a href='/SOFTLOT/faces/jsp/login/root.jsp?url="+urlCodificada+"&tit="+_tmpOption.getName()+"' style='text-decoration:none;' target='"+_tmpOption.getOptionId()+"' name='"+_tmpOption.getName()+"'>"+_tmpOption.getName()+"</a></li></ul> ";
					}
				} else {
					
					if(_tmpOption.getOrder() == 1){
						output += 	"<h3><a style='color: white;' href='#'>"+_tmpOption.getName()+"</a></h3> "+
		    						"<div> ";
						
						output += getNavigatorItems(_tmpOption.getSuboptions());
						
						output += 	"</div> ";
					}else{
						
						output += 	"<ul id='nav-sub'>" +
										"<li class='parent-here sub-level-"+(_tmpOption.getOrder()-2)+"'><a href='#'>"+_tmpOption.getName()+"</a>"+   
					    					"<ul>";
						
						output += getNavigatorItems(_tmpOption.getSuboptions());
						
						output += 			"</ul>"+
										"</li>" +
									"</ul>";
					}
				}
			}
		}
		return output;
	}
	
	public Object getSessionAttribute(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public void removeSessionAttribute(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public void setRoleLogic() {
		try {
			if (this.roleLogic == null) {
				String springFilesConfig = getServletConfig()
						.getServletContext().getInitParameter(
								"contextConfigLocation");

				if (springFilesConfig != null && !springFilesConfig.equals("")) {
					StringTokenizer stok = new StringTokenizer(
							springFilesConfig, ",");
					String resource[] = new String[stok.countTokens()];
					int i = 0;
					while (stok.hasMoreTokens()) {
						String recurso = stok.nextToken().trim();
						resource[i] = recurso;
						i++;
					}
					appContext = new ClassPathXmlApplicationContext(resource);
					beanFactory = appContext;
				}
				if (beanFactory != null) {
					this.roleLogic = (IRoleLogic) beanFactory
							.getBean("roleLogic");
				} else {
					throw new Exception("Error al cargar el contexto.");
				}
			}
		} catch (Exception e) {
			log.error("setRoleLogic()" + e);
		}
	}

	public IRoleLogic getRoleLogic() {
		return roleLogic;
	}

	public void setEmployeeLogic() {
		try {
			if (this.employeeLogic == null) {
				String springFilesConfig = getServletConfig()
						.getServletContext().getInitParameter(
								"contextConfigLocation");

				if (springFilesConfig != null && !springFilesConfig.equals("")) {
					StringTokenizer stok = new StringTokenizer(
							springFilesConfig, ",");
					String resource[] = new String[stok.countTokens()];
					int i = 0;
					while (stok.hasMoreTokens()) {
						String recurso = stok.nextToken().trim();
						resource[i] = recurso;
						i++;
					}
					appContext = new ClassPathXmlApplicationContext(resource);
					beanFactory = appContext;
				}
				if (beanFactory != null) {
					this.employeeLogic = (IEmployeeLogic) beanFactory
							.getBean("employeeLogic");
				} else {
					throw new Exception("Error al cargar el contexto.");
				}
			}
		} catch (Exception e) {
			log.error("setEmployeeLogic()" + e);
		}
	}

	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	public void setResourceLogic() {
		try {
			if (this.resourceLogic == null) {
				String springFilesConfig = getServletConfig()
						.getServletContext().getInitParameter(
								"contextConfigLocation");

				if (springFilesConfig != null && !springFilesConfig.equals("")) {
					StringTokenizer stok = new StringTokenizer(
							springFilesConfig, ",");
					String resource[] = new String[stok.countTokens()];
					int i = 0;
					while (stok.hasMoreTokens()) {
						String recurso = stok.nextToken().trim();
						resource[i] = recurso;
						i++;
					}
					appContext = new ClassPathXmlApplicationContext(resource);
					beanFactory = appContext;
				}
				if (beanFactory != null) {
					this.resourceLogic = (IResourceLogic) beanFactory
							.getBean("resourceLogic");
				} else {
					throw new Exception("Error al cargar el contexto.");
				}
			}
		} catch (Exception e) {
			log.error("setResourceLogic()" + e);
		}
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}
}
