package com.cwttech.alert.impl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cwttech.alert.base.Context;
import com.cwttech.alert.base.ISendService;
import com.cwttech.alert.model.EmailContext;

public class EmailSendService extends ISendService{

	@Override
	public void send(Context context) {
		//创建邮件
		createMessage((EmailContext)context);
		//执行发送操作
		sendMessage((EmailContext)context);
	}
	
	public Message createMessage(EmailContext context){
    	// 用session为参数定义消息对象
    	MimeMessage message = new MimeMessage(context.getSession());
    	try {
		   // 加载发件人地址
		   String fromAddress=String.valueOf(context.getFrom());
		   message.setFrom(new InternetAddress(fromAddress,"信息发送平台"));
		   // 加载收件人地址
		   message.setRecipients(Message.RecipientType.TO, String.valueOf(context.getTo()));
		   // 加载标题
		   message.setSubject(String.valueOf(context.getMsg()));
		   //向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
		   Multipart multipart = new MimeMultipart();
		   // 设置邮件的文本内容
		   MimeBodyPart contentPart = new MimeBodyPart();
		   contentPart.setContent(String.valueOf(context.getMsg()),"text/html;charset=utf-8");
		   multipart.addBodyPart(contentPart);
		   // 将multipart对象放到message中
		   message.setContent(multipart);
		   // 保存邮件
		   message.saveChanges();
		} catch (Exception e) {
    		e.printStackTrace();
    	}
		 return message;
    }
	/**
     * 发送邮件
     */
    public boolean sendMessage(EmailContext context) {
    	boolean flag = true;
    	try {
    		context.getTransport().sendMessage(context.getMessage(), context.getMessage().getAllRecipients());
    	} catch (AddressException e) {
    		e.printStackTrace();
    		flag = false;
    	} catch (MessagingException e) {
    		e.printStackTrace();
    		flag = false;
    	} catch (Exception e) {
    		e.printStackTrace();
    		flag = false;
    	}
    	return flag;
    }
}
