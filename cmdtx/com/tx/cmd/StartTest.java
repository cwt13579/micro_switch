package com.tx.cmd;

import com.tx.cmd.core.OPCaller;
import com.tx.cmd.core.OperationContext;
import com.tx.cmd.operation.DemoOperation;
import com.tx.cmd.utils.ApplicationContextUtils;

public class StartTest {

	public static void main(String args[]) throws Exception {
		ApplicationContextUtils.init("classpath:applicationContext_oracle.xml");
		OperationContext oc = new OperationContext();
		oc.setAttribute(DemoOperation.CMD, "democmd");
 		oc.setAttribute(DemoOperation.APPLY_IN, "demo");
		
		//DemoOperation op = ApplicationContextUtils.getContext().getBean(DemoOperation.class);
		OPCaller.call(DemoOperation.class, oc);
	}
}
