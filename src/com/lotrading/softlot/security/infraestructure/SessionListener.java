package com.lotrading.softlot.security.infraestructure;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Implements HttpSessionListener
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class SessionListener implements HttpSessionListener{

	private int sessionCount;

	public SessionListener(){

	}

	public void finalize() throws Throwable {

	}

	public int getSessionCount(){
		return sessionCount;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSessionCount(int newVal){
		sessionCount = newVal;
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}