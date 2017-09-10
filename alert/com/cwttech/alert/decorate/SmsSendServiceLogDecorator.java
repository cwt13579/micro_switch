package com.cwttech.alert.decorate;

import com.cwttech.alert.base.Context;
import com.cwttech.alert.base.ISendService;

public class SmsSendServiceLogDecorator extends ISendService{

	private final ISendService sendService;
	
	public SmsSendServiceLogDecorator(ISendService sendService) {
		this.sendService = sendService;
	}
	@Override
	public void send(Context context) {
		//保存日志
		saveLog(context);
		//发送信息
		sendService.send(context);
	}
	
	private void saveLog(Context context) {
		//保存到数据库
	}

}
