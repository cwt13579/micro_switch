package com.cwttech.alert.factory;

import com.cwttech.alert.base.ISendService;
import com.cwttech.alert.decorate.EmailSendServiceLogDecorator;
import com.cwttech.alert.decorate.EmailSendServiceRetryDecorator;
import com.cwttech.alert.impl.EmailSendService;

public class EmailSendDecoratorBuilder {
    private ISendService sendService = new EmailSendService();
    
    public EmailSendDecoratorBuilder log() {
    	sendService = new  EmailSendServiceLogDecorator(sendService);
    	return this;
    }
    
    public EmailSendDecoratorBuilder retry() {
    	sendService = new  EmailSendServiceRetryDecorator(sendService);
    	return this;
    }
    
    public ISendService build() {
    	ISendService ss = sendService;
    	sendService = new EmailSendService();
    	return ss;
    }
}
