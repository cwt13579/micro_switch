package com.cwttech.alert.decorate;

import com.cwttech.alert.base.Context;
import com.cwttech.alert.base.ISendService;

public class EmailSendServiceRetryDecorator extends ISendService{
private final ISendService  sendService;
	
	public EmailSendServiceRetryDecorator(ISendService  sendService) {
		this.sendService = sendService;
	}
	@Override
	public void send(Context context) {
		executeWithRetries(context);
	}
	
	private void executeWithRetries(Context context) {
		for(int i = 0 ; i < 3; i++) {
			try {
				sendService.send(context);
			} catch(Exception e) {
				//e.printStackTrace();
				continue;
			}
			break;
		}
	}
}
