package com.bell.buss.services;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.bell.buss.bean.Purchase;
import com.bell.buss.bean.TestInputBean;
import com.bell.buss.bean.TestOutputBean;

public class TestService1 {
	private static Logger log = Logger.getLogger(TestService1.class);

	public TestOutputBean test(TestInputBean tib)throws Exception{
		log.info("测试");
		System.out.println(tib.toString());
		TestOutputBean outBean = new TestOutputBean();
		outBean.setHeadBean(tib.getHeadBean());
		outBean.setRspcode("SUCCESS");
		outBean.setRspmsg("sucess");
		Purchase p = new Purchase();
		p.setCurrency("RMB");
		p.setPurchAmount(new BigDecimal("100000.01"));
		p.setExponent("1");
		p.setPurchAmount(new BigDecimal("200000.11"));
		outBean.getArray().add(p);
		
		Purchase p1 = new Purchase();
		p1.setCurrency("RMB");
		p1.setPurchAmount(new BigDecimal("100000.01"));
		p1.setExponent("1");
		p1.setPurchAmount(new BigDecimal("200000.11"));
		outBean.getArray().add(p1);
		//throw new AppException("","9999","default",null);
//		OperationContext oc = new OperationContext();
//		oc.setAttribute(EbankSwitchOperation.PARAM_CODE, tib.getVersion());
//		oc.setAttribute(EbankSwitchOperation.PARAM_IN, tib);
//		OPCaller.call(EbankSwitchOperation.ID, oc);
//		TestOutputBean outBean = (TestOutputBean)oc.getAttribute(EbankSwitchOperation.OUT_PARAM);
		return outBean;
	}
 
}
