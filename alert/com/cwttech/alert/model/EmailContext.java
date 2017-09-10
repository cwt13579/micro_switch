package com.cwttech.alert.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import com.cwttech.alert.base.Context;
import com.cwttech.alert.common.PropKit;

public class EmailContext extends Context{

	private final static String MAIL_SMTP_HOST = "mail.smtp.host";
	private final static String MAIL_SMTP_PORT = "mail.smtp.port";
	private final static String MAIL_SMTP_AUTH = "mail.smtp.auth";
	    
	private static Properties props = new Properties();

    private static final String propertiesFilePath="MideaMail";
    private static final String FROMADDRESS="FROMADDRESS";
    private static final String SERVERHOST="SERVERHOST";
    private static final String PORT="PORT";
	private static final String USERNAME="USERNAME";
	private static final String PASSWORD="PASSWORD";	
	
	private static Session session;        //会话
	private static Transport transport;    //发送邮件	
	private Message message;               //邮件内容
	
	static{
		try {
			  PropKit.use(propertiesFilePath);
			  if (props == null) {
		            props = System.getProperties();
		            props.put(MAIL_SMTP_AUTH, "true");
		            props.put(MAIL_SMTP_HOST, PropKit.get(SERVERHOST));
		 	        props.put(MAIL_SMTP_PORT, PropKit.get(PORT));
		 	        SmtpAuth sa = new SmtpAuth(PropKit.get(USERNAME), PropKit.get(PASSWORD));
		 	        session = Session.getInstance(props, sa);
			        session.setDebug(true);
			        transport = session.getTransport("smtp");
		        }
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	public Object getFrom() {
		if(attrs.get(FROM) == null) {
			return PropKit.get(FROMADDRESS);
		}
		return attrs.get(FROM);
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Session getSession() {
		return session;
	}
	public Transport getTransport() {
		return transport;
	}
}
