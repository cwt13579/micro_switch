package com.tx.cmd.data;

import com.tx.cmd.data.base.BaseTblDebtBaseInfo;



public class TblDebtBaseInfo extends BaseTblDebtBaseInfo {
	private static final long serialVersionUID = 1L;

	private String mastContCode;//新增信贷合同信息
	
/*[CONSTRUCTOR MARKER BEGIN]*/
	public TblDebtBaseInfo () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TblDebtBaseInfo (java.lang.String id) {
		super(id);
	}

	/**
	 * @return the mastContCode
	 */
	public String getMastContCode() {
		return mastContCode;
	}

	/**
	 * @param mastContCode the mastContCode to set
	 */
	public void setMastContCode(String mastContCode) {
		this.mastContCode = mastContCode;
	}

/*[CONSTRUCTOR MARKER END]*/


}