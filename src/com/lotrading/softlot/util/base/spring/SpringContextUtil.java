package com.lotrading.softlot.util.base.spring;

import java.util.StringTokenizer;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtil {
    private static ApplicationContext appContext;
    private static BeanFactory beanFactory;
    private static Log log = LogFactory.getLog(SpringContextUtil.class);
    
    static {
        try {
            
            FacesContext ctx = FacesContext.getCurrentInstance();
            
            if (ctx == null) {
                throw new RuntimeException("");
            }
            
            String springFilesConfig = ctx.getExternalContext().getInitParameter("contextConfigLocation");
            
            if (springFilesConfig != null && !springFilesConfig.equals("")) {
                StringTokenizer stok = new StringTokenizer(springFilesConfig, ",");
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
            else {
                throw new RuntimeException("");
            }
            
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    
    public static synchronized Object getBean(String beanName) throws Exception {
        Object _tmpReturn = null;
        try {
            if (beanFactory != null) {
            	_tmpReturn =  beanFactory.getBean(beanName);
            } else {
                throw new RuntimeException("");
            }
        } catch (Exception e) {
            log.error("error", e);
            throw e;
        }
        return _tmpReturn;
    }
}