/*
 * Created on 2005-5-11
*
*/
package com.tx.cmd.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tx.cmd.utils.ApplicationContextUtils;


/**
* @author yujianjun
*
* Operation caller.
*/
public class OPCaller {
	private final static Log log = LogFactory.getLog(OPCaller.class);

	public void callOperation(BaseOperation operation, OperationContext context)
			throws Exception {
		try {
			operation.beforeProc(context);
			operation.execute(context);
			operation.afterProc(context);
		} catch (Exception ce) {
			throw ce;
		} catch (Throwable t) {
			throw t;
		}
	}



	/**
	 * 调用以beanName指定的Operation，Operation的参数为context.
	 *
	 * @param beanName
	 *            operation's bean name
	 * @param context
	 *            输入输出参数
	 * @throws Exception
	 *             异常发生
	 */
	public static void call(Class clz, OperationContext context)
			throws Exception {
		// modify by shen_antonio 20080508
		String errCd = null;
		OPCaller caller = (OPCaller) ApplicationContextUtils
				.getBean("operationCaller");
		BaseOperation operation = (BaseOperation) ApplicationContextUtils
				.getBean(clz);
		try {
			// start/end a transaction
			if (operation.isNeedLog()) {
				//caller.doTlsrno();
			}
			caller.callOperation(operation, context);
		} catch (Exception cex) {
			cex.printStackTrace();
			throw cex;
		} finally {
			// 成功交易
			if (errCd == null) {
				// 记录日志 -- 只有成功时才记录.
				if (operation.isNeedLog()) {
					try {
						//doLog(errCd,context);
					} catch (Throwable t) {
						log.error("记录日志时发生异常。", t);
					}
				}
			// 失败交易
			} else {
				if (operation.isNeedFailLog()) {
					try {
						//doLog(errCd,context);
					} catch (Throwable t) {
						log.error("记录日志时发生异常。", t);
					}
				}
			}
		}
	}



//	/**
//	 * 记录日志。
//	 *
//	 * @throws Exception
//	 */
//	private static void doLog(String errorCode,OperationContext context) throws Exception {
//		GlobalData globalInfo = GlobalData.getCurrentInstanceWithoutException();
//       if (null == globalInfo) {
//           log.warn("globalInfo为空,不能记录业务日志。");
//           return;
//       }
//
//       BizLog bizLog = new BizLog();
//       bizLog.setTxdate(globalInfo.getTxnDate8());
//       bizLog.setBrcode(DataFormat.trim(globalInfo.getBrcode()));
//       bizLog.setTlrno(DataFormat.trim(globalInfo.getTlrno()));
//       bizLog.setTlsrno(DataFormat.trim(globalInfo.getTlsrno()));
//       bizLog.setSubBrcode(DataFormat.trim(globalInfo.getBrcode()));
//       bizLog.setTxtime(globalInfo.getTxtime());
//       bizLog.setFuncCode(globalInfo.getFuncCode());
//       bizLog.setErrCode(DataFormat.trim(errorCode));
//       bizLog.setCurcd(SystemConstant.CURCD_RMB);
//
//       if (globalInfo.getContractnoFlag().equals(SystemConstant.FLAG_ON))
//           bizLog.setContractno(DataFormat.trim(globalInfo.getContractno()));
//       if (globalInfo.getCustcdFlag().equals(SystemConstant.FLAG_ON))
//           bizLog.setCustcd(DataFormat.trim(globalInfo.getCustcd()));
//       if (globalInfo.getAppnoFlag().equals(SystemConstant.FLAG_ON))
//           bizLog.setAppno(DataFormat.trim(globalInfo.getAppno()));
//       if (globalInfo.getTxamtFlag().equals(SystemConstant.FLAG_ON))
//           bizLog.setTxamt(new BigDecimal(globalInfo.getTxamt()));
//       if (globalInfo.isPlTlsrnoFlag()) {
//       	bizLog.setPlTlsrno(globalInfo.getPlTlsrno());
//       }
//       //记录bizlog中的misc信息
//       if( context!=null && context.getAttribute(SystemConstant.CONTEXT_BIZ_LOG_MISC)!=null ){
//       	bizLog.setMisc(context.getAttribute(SystemConstant.CONTEXT_BIZ_LOG_MISC).toString());
//       }
//       LogSeqService.getInstance().saveBizLog(bizLog);
//   }
}