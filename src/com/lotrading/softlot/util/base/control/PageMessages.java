package com.lotrading.softlot.util.base.control;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PageMessages implements Serializable
{
    private static final long serialVersionUID = -6479897299239746841L;
    private static final String BEAN_NAME = PageMessages.class.getName();
    private String messageHeader;
    private String messageImage;
    private Severity severityLevel;
    private Log log = LogFactory.getLog(PageMessages.class);

    public PageMessages() {
        messageHeader = null;
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // See if there are messages queued for the page
        severityLevel = ctx.getMaximumSeverity();

        if (null != severityLevel) {
            log.debug("Severity Level Trapped: level = '" + severityLevel.toString() + "'");

            if (severityLevel.compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
                messageHeader = null;
                messageImage = "/css/images/error.png";
            } else if (severityLevel.compareTo(FacesMessage.SEVERITY_INFO) == 0) {
                messageHeader = null;
                messageImage = "/css/images/info.png";
            } else if (severityLevel.compareTo(FacesMessage.SEVERITY_WARN) == 0) {
                messageHeader = null;
                messageImage = "/css/images/advertencia.png";
            } else if (severityLevel.compareTo(FacesMessage.SEVERITY_FATAL) == 0) {
                messageHeader = null;
                messageImage = "/css/images/error.png";
            }
        } else {
            log.debug("Severity Level Trapped: level = 'null'");
        }
    }

    public Boolean getRenderMessage() {
        return new Boolean(getMessageHeader()!=null && !getMessageHeader().isEmpty());
    }

    public String getBeanName() {
        return BEAN_NAME;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public String getMessageImage() {
        return messageImage;
    }
}