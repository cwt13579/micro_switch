package com.tx.cmd.operation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.cmd.core.BaseOperation;
import com.tx.cmd.core.OperationContext;
import com.tx.cmd.dao.TblDebtBaseInfoDAO;
import com.tx.cmd.data.TblDebtBaseInfo;

@Service
public class DemoOperation extends BaseOperation{
	public static final String ID = "com.tx.cmd.operation.DemoOperation";
	public static final String CMD = "CMD";
	public static final String APPLY_IN = "APPLY_IN";
	public static final String OUT_RESULT = "OUT_RESULT";
	
	public DemoOperation() {
		System.out.println("===============");
	}
	@Autowired
	private TblDebtBaseInfoDAO tblDebtBaseInfoDAO;
	@Override
	public void beforeProc(OperationContext context) throws Exception {
		 
		
	}

	@Override
	public void execute(OperationContext context) throws Exception {
	    String cmd = (String)context.getAttribute(CMD);
		System.out.println("cmd:"+cmd);
		TblDebtBaseInfo tblDebtBaseInfo = tblDebtBaseInfoDAO.get("1BK38A71U03J680D0B6200002980C64D");
		tblDebtBaseInfo.setBillsAmount(new BigDecimal("200000.00"));
	    //操作 service dao
		tblDebtBaseInfoDAO.update(tblDebtBaseInfo);
		
	    context.setAttribute(OUT_RESULT, "demoresult");
	    //throw new Exception("my error");
	}

	@Override
	public void afterProc(OperationContext context) throws Exception {
		 
		
	}

}
