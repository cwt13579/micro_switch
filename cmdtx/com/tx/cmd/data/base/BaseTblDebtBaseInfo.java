package com.tx.cmd.data.base;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tx.cmd.data.TblDebtBaseInfo;

/**
 * This is an object that contains data related to the TBL_DEBT_BASE_INFO table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 *
 * @hibernate.class table="TBL_DEBT_BASE_INFO"
 */

public abstract class BaseTblDebtBaseInfo implements Serializable {

	private static final long serialVersionUID = -7190411472528025993L;
	// constructors
	public BaseTblDebtBaseInfo() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTblDebtBaseInfo(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String custcdSaller;
	private java.lang.String cnameBuyer;
	private java.lang.String custcdBuyer;
	private java.lang.String cnameSeller;
	private java.lang.String mastContno;
	private java.lang.String debetNo;
	private java.util.Date insertDate;
	private java.lang.String aging;
	private java.lang.String bussType;
	private java.lang.String billsNo;
	private java.lang.String billsType;
	private java.util.Date billsDate;
	private java.util.Date debtEnd;
	private java.math.BigDecimal billsAmount;
	private java.math.BigDecimal billsAmountView;
	private java.math.BigDecimal remainingAmount;
	private java.lang.String curcd;
	private java.math.BigDecimal loanPercent;
	private java.math.BigDecimal loanAmount;
	private java.util.Date purchaseDate;
	private java.math.BigDecimal loanRemainingAmount;
	private java.math.BigDecimal remainingUseableAmount;
	private java.math.BigDecimal rateCollectAmount;
	private java.math.BigDecimal ownRateCollectAmount;
	private java.util.Date loanEndDate;
	private java.math.BigDecimal assurePercent;
	private java.util.Date assurePayDate;
	private java.math.BigDecimal rebateAmount;
	private java.math.BigDecimal buyBackAmount;
	private java.math.BigDecimal payAmount;
	private java.math.BigDecimal removeAmount;
	private java.math.BigDecimal cashRemainingAmount;
	private java.lang.String flawFlag;
	private java.lang.String flawReason;
	private java.lang.String issueFlag;
	private java.lang.String issueReason;
	private java.lang.String overdueFlag;
	private java.lang.String overdueReason;
	private java.lang.String badFlag;
	private java.lang.String badReason;
	private java.lang.String poolFlag;
	private java.math.BigDecimal overdueAmount;
	private java.lang.String pledgeStatus;
	private java.lang.String overdueStatus;
	private java.lang.String status;
	private java.lang.String returnStatus;
	private java.util.Date pledgeActiveDate;
	private java.util.Date unpledgeActiveDate;
	private java.lang.String memo;
	private Integer pressCount;
	private Integer warnCount;
	private java.lang.String billsPeriod;
	private java.util.Date beginDate;
	private java.util.Date endDate;
	private java.util.Date paydate;
	private java.lang.String loanOverdueFlag;
	private java.lang.String deleteReason;
	private java.lang.String otherReason;
	private java.lang.String reason;
	private java.math.BigDecimal bailAmount;
	private java.math.BigDecimal addBailAmount;
	private java.lang.String isLocked;
	private java.lang.String lockAppno;
	private java.lang.String factType;
	private java.lang.String loanWay;
	private java.lang.String bussContcode;
	private java.util.Date deadLine;
	private java.lang.Integer gracePeriod;
	private java.lang.String debtContno;
	private java.lang.String claimStatus;
	private java.util.Date confirmDate;
	private String ebankAppno;
	private String channelFlag;
	private Integer issueTimes;
	private String isPay;
	private String creditNo;
    private BigDecimal bussContAmt;
    private BigDecimal bussPayAmt;
    
	public BigDecimal getBussContAmt() {
		return bussContAmt;
	}

	public void setBussContAmt(BigDecimal bussContAmt) {
		this.bussContAmt = bussContAmt;
	}

	public BigDecimal getBussPayAmt() {
		return bussPayAmt;
	}

	public void setBussPayAmt(BigDecimal bussPayAmt) {
		this.bussPayAmt = bussPayAmt;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public Integer getIssueTimes() {
		return issueTimes;
	}

	public void setIssueTimes(Integer issueTimes) {
		this.issueTimes = issueTimes;
	}

	public java.util.Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(java.util.Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public java.lang.String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(java.lang.String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getEbankAppno() {
		return ebankAppno;
	}

	public void setEbankAppno(String ebankAppno) {
		this.ebankAppno = ebankAppno;
	}

	public String getChannelFlag() {
		return channelFlag;
	}

	public void setChannelFlag(String channelFlag) {
		this.channelFlag = channelFlag;
	}

	/**
	 * Return the unique identifier of this class
	 *
	 * @hibernate.id generator-class="assigned" column="ID"
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 *
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: CUSTCD_SALLER
	 */
	public java.lang.String getCustcdSaller() {
		return custcdSaller;
	}

	/**
	 * Set the value related to the column: CUSTCD_SALLER
	 *
	 * @param custcdSaller
	 *            the CUSTCD_SALLER value
	 */
	public void setCustcdSaller(java.lang.String custcdSaller) {
		this.custcdSaller = custcdSaller;
	}

	/**
	 * Return the value associated with the column: CNAME_BUYER
	 */
	public java.lang.String getCnameBuyer() {
		return cnameBuyer;
	}

	/**
	 * Set the value related to the column: CNAME_BUYER
	 *
	 * @param cnameBuyer
	 *            the CNAME_BUYER value
	 */
	public void setCnameBuyer(java.lang.String cnameBuyer) {
		this.cnameBuyer = cnameBuyer;
	}

	/**
	 * Return the value associated with the column: CUSTCD_BUYER
	 */
	public java.lang.String getCustcdBuyer() {
		return custcdBuyer;
	}

	/**
	 * Set the value related to the column: CUSTCD_BUYER
	 *
	 * @param custcdBuyer
	 *            the CUSTCD_BUYER value
	 */
	public void setCustcdBuyer(java.lang.String custcdBuyer) {
		this.custcdBuyer = custcdBuyer;
	}

	/**
	 * Return the value associated with the column: CNAME_SELLER
	 */
	public java.lang.String getCnameSeller() {
		return cnameSeller;
	}

	/**
	 * Set the value related to the column: CNAME_SELLER
	 *
	 * @param cnameSeller
	 *            the CNAME_SELLER value
	 */
	public void setCnameSeller(java.lang.String cnameSeller) {
		this.cnameSeller = cnameSeller;
	}

	/**
	 * Return the value associated with the column: MAST_CONTNO
	 */
	public java.lang.String getMastContno() {
		return mastContno;
	}

	/**
	 * Set the value related to the column: MAST_CONTNO
	 *
	 * @param mastContno
	 *            the MAST_CONTNO value
	 */
	public void setMastContno(java.lang.String mastContno) {
		this.mastContno = mastContno;
	}

	/**
	 * Return the value associated with the column: DEBET_NO
	 */
	public java.lang.String getDebetNo() {
		return debetNo;
	}

	/**
	 * Set the value related to the column: DEBET_NO
	 *
	 * @param debetNo
	 *            the DEBET_NO value
	 */
	public void setDebetNo(java.lang.String debetNo) {
		this.debetNo = debetNo;
	}

	/**
	 * Return the value associated with the column: INSERT_DATE
	 */
	public java.util.Date getInsertDate() {
		return insertDate;
	}

	/**
	 * Set the value related to the column: INSERT_DATE
	 *
	 * @param insertDate
	 *            the INSERT_DATE value
	 */
	public void setInsertDate(java.util.Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * Return the value associated with the column: AGING
	 */
	public java.lang.String getAging() {
		return aging;
	}

	/**
	 * Set the value related to the column: AGING
	 *
	 * @param aging
	 *            the AGING value
	 */
	public void setAging(java.lang.String aging) {
		this.aging = aging;
	}

	/**
	 * Return the value associated with the column: BUSS_TYPE
	 */
	public java.lang.String getBussType() {
		return bussType;
	}

	/**
	 * Set the value related to the column: BUSS_TYPE
	 *
	 * @param bussType
	 *            the BUSS_TYPE value
	 */
	public void setBussType(java.lang.String bussType) {
		this.bussType = bussType;
	}

	/**
	 * Return the value associated with the column: BILLS_NO
	 */
	public java.lang.String getBillsNo() {
		return billsNo;
	}

	/**
	 * Set the value related to the column: BILLS_NO
	 *
	 * @param billsNo
	 *            the BILLS_NO value
	 */
	public void setBillsNo(java.lang.String billsNo) {
		this.billsNo = billsNo;
	}

	/**
	 * Return the value associated with the column: BILLS_TYPE
	 */
	public java.lang.String getBillsType() {
		return billsType;
	}

	/**
	 * Set the value related to the column: BILLS_TYPE
	 *
	 * @param billsType
	 *            the BILLS_TYPE value
	 */
	public void setBillsType(java.lang.String billsType) {
		this.billsType = billsType;
	}

	/**
	 * Return the value associated with the column: BILLS_DATE
	 */
	public java.util.Date getBillsDate() {
		return billsDate;
	}

	/**
	 * Set the value related to the column: BILLS_DATE
	 *
	 * @param billsDate
	 *            the BILLS_DATE value
	 */
	public void setBillsDate(java.util.Date billsDate) {
		this.billsDate = billsDate;
	}

	/**
	 * Return the value associated with the column: DEBT_END
	 */
	public java.util.Date getDebtEnd() {
		return debtEnd;
	}

	/**
	 * Set the value related to the column: DEBT_END
	 *
	 * @param debtEnd
	 *            the DEBT_END value
	 */
	public void setDebtEnd(java.util.Date debtEnd) {
		this.debtEnd = debtEnd;
	}

	/**
	 * Return the value associated with the column: BILLS_AMOUNT
	 */
	public java.math.BigDecimal getBillsAmount() {
		return billsAmount;
	}

	/**
	 * Set the value related to the column: BILLS_AMOUNT
	 *
	 * @param billsAmount
	 *            the BILLS_AMOUNT value
	 */
	public void setBillsAmount(java.math.BigDecimal billsAmount) {
		this.billsAmount = billsAmount;
	}

	/**
	 * Return the value associated with the column: BILLS_AMOUNT_VIEW
	 */
	public java.math.BigDecimal getBillsAmountView() {
		return billsAmountView;
	}

	/**
	 * Set the value related to the column: BILLS_AMOUNT_VIEW
	 *
	 * @param billsAmountView
	 *            the BILLS_AMOUNT_VIEW value
	 */
	public void setBillsAmountView(java.math.BigDecimal billsAmountView) {
		this.billsAmountView = billsAmountView;
	}

	/**
	 * Return the value associated with the column: REMAINING_AMOUNT
	 */
	public java.math.BigDecimal getRemainingAmount() {
		return remainingAmount;
	}

	/**
	 * Set the value related to the column: REMAINING_AMOUNT
	 *
	 * @param remainingAmount
	 *            the REMAINING_AMOUNT value
	 */
	public void setRemainingAmount(java.math.BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	/**
	 * Return the value associated with the column: CURCD
	 */
	public java.lang.String getCurcd() {
		return curcd;
	}

	/**
	 * Set the value related to the column: CURCD
	 *
	 * @param curcd
	 *            the CURCD value
	 */
	public void setCurcd(java.lang.String curcd) {
		this.curcd = curcd;
	}

	/**
	 * Return the value associated with the column: LOAN_PERCENT
	 */
	public java.math.BigDecimal getLoanPercent() {
		return loanPercent;
	}

	/**
	 * Set the value related to the column: LOAN_PERCENT
	 *
	 * @param loanPercent
	 *            the LOAN_PERCENT value
	 */
	public void setLoanPercent(java.math.BigDecimal loanPercent) {
		this.loanPercent = loanPercent;
	}

	/**
	 * Return the value associated with the column: LOAN_AMOUNT
	 */
	public java.math.BigDecimal getLoanAmount() {
		return loanAmount;
	}

	/**
	 * Set the value related to the column: LOAN_AMOUNT
	 *
	 * @param loanAmount
	 *            the LOAN_AMOUNT value
	 */
	public void setLoanAmount(java.math.BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * Return the value associated with the column: PURCHASE_DATE
	 */
	public java.util.Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Set the value related to the column: PURCHASE_DATE
	 *
	 * @param purchaseDate
	 *            the PURCHASE_DATE value
	 */
	public void setPurchaseDate(java.util.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Return the value associated with the column: LOAN_REMAINING_AMOUNT
	 */
	public java.math.BigDecimal getLoanRemainingAmount() {
		return loanRemainingAmount;
	}

	/**
	 * Set the value related to the column: LOAN_REMAINING_AMOUNT
	 *
	 * @param loanRemainingAmount
	 *            the LOAN_REMAINING_AMOUNT value
	 */
	public void setLoanRemainingAmount(java.math.BigDecimal loanRemainingAmount) {
		this.loanRemainingAmount = loanRemainingAmount;
	}

	/**
	 * Return the value associated with the column: REMAINING_USEABLE_AMOUNT
	 */
	public java.math.BigDecimal getRemainingUseableAmount() {
		return remainingUseableAmount;
	}

	/**
	 * Set the value related to the column: REMAINING_USEABLE_AMOUNT
	 *
	 * @param remainingUseableAmount
	 *            the REMAINING_USEABLE_AMOUNT value
	 */
	public void setRemainingUseableAmount(
			java.math.BigDecimal remainingUseableAmount) {
		this.remainingUseableAmount = remainingUseableAmount;
	}

	/**
	 * Return the value associated with the column: RATE_COLLECT_AMOUNT
	 */
	public java.math.BigDecimal getRateCollectAmount() {
		return rateCollectAmount;
	}

	/**
	 * Set the value related to the column: RATE_COLLECT_AMOUNT
	 *
	 * @param rateCollectAmount
	 *            the RATE_COLLECT_AMOUNT value
	 */
	public void setRateCollectAmount(java.math.BigDecimal rateCollectAmount) {
		this.rateCollectAmount = rateCollectAmount;
	}

	/**
	 * Return the value associated with the column: OWN_RATE_COLLECT_AMOUNT
	 */
	public java.math.BigDecimal getOwnRateCollectAmount() {
		return ownRateCollectAmount;
	}

	/**
	 * Set the value related to the column: OWN_RATE_COLLECT_AMOUNT
	 *
	 * @param ownRateCollectAmount
	 *            the OWN_RATE_COLLECT_AMOUNT value
	 */
	public void setOwnRateCollectAmount(
			java.math.BigDecimal ownRateCollectAmount) {
		this.ownRateCollectAmount = ownRateCollectAmount;
	}

	/**
	 * Return the value associated with the column: LOAN_END_DATE
	 */
	public java.util.Date getLoanEndDate() {
		return loanEndDate;
	}

	/**
	 * Set the value related to the column: LOAN_END_DATE
	 *
	 * @param loanEndDate
	 *            the LOAN_END_DATE value
	 */
	public void setLoanEndDate(java.util.Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	/**
	 * Return the value associated with the column: ASSURE_PERCENT
	 */
	public java.math.BigDecimal getAssurePercent() {
		return assurePercent;
	}

	/**
	 * Set the value related to the column: ASSURE_PERCENT
	 *
	 * @param assurePercent
	 *            the ASSURE_PERCENT value
	 */
	public void setAssurePercent(java.math.BigDecimal assurePercent) {
		this.assurePercent = assurePercent;
	}

	/**
	 * Return the value associated with the column: ASSURE_PAY_DATE
	 */
	public java.util.Date getAssurePayDate() {
		return assurePayDate;
	}

	/**
	 * Set the value related to the column: ASSURE_PAY_DATE
	 *
	 * @param assurePayDate
	 *            the ASSURE_PAY_DATE value
	 */
	public void setAssurePayDate(java.util.Date assurePayDate) {
		this.assurePayDate = assurePayDate;
	}

	/**
	 * Return the value associated with the column: REBATE_AMOUNT
	 */
	public java.math.BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * Set the value related to the column: REBATE_AMOUNT
	 *
	 * @param rebateAmount
	 *            the REBATE_AMOUNT value
	 */
	public void setRebateAmount(java.math.BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	/**
	 * Return the value associated with the column: BUY_BACK_AMOUNT
	 */
	public java.math.BigDecimal getBuyBackAmount() {
		return buyBackAmount;
	}

	/**
	 * Set the value related to the column: BUY_BACK_AMOUNT
	 *
	 * @param buyBackAmount
	 *            the BUY_BACK_AMOUNT value
	 */
	public void setBuyBackAmount(java.math.BigDecimal buyBackAmount) {
		this.buyBackAmount = buyBackAmount;
	}

	/**
	 * Return the value associated with the column: PAY_AMOUNT
	 */
	public java.math.BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * Set the value related to the column: PAY_AMOUNT
	 *
	 * @param payAmount
	 *            the PAY_AMOUNT value
	 */
	public void setPayAmount(java.math.BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * Return the value associated with the column: REMOVE_AMOUNT
	 */
	public java.math.BigDecimal getRemoveAmount() {
		return removeAmount;
	}

	/**
	 * Set the value related to the column: REMOVE_AMOUNT
	 *
	 * @param removeAmount
	 *            the REMOVE_AMOUNT value
	 */
	public void setRemoveAmount(java.math.BigDecimal removeAmount) {
		this.removeAmount = removeAmount;
	}

	/**
	 * Return the value associated with the column: CASH_REMAINING_AMOUNT
	 */
	public java.math.BigDecimal getCashRemainingAmount() {
		return cashRemainingAmount;
	}

	/**
	 * Set the value related to the column: CASH_REMAINING_AMOUNT
	 *
	 * @param cashRemainingAmount
	 *            the CASH_REMAINING_AMOUNT value
	 */
	public void setCashRemainingAmount(java.math.BigDecimal cashRemainingAmount) {
		this.cashRemainingAmount = cashRemainingAmount;
	}

	/**
	 * Return the value associated with the column: FLAW_FLAG
	 */
	public java.lang.String getFlawFlag() {
		return flawFlag;
	}

	/**
	 * Set the value related to the column: FLAW_FLAG
	 *
	 * @param flawFlag
	 *            the FLAW_FLAG value
	 */
	public void setFlawFlag(java.lang.String flawFlag) {
		this.flawFlag = flawFlag;
	}

	/**
	 * Return the value associated with the column: FLAW_REASON
	 */
	public java.lang.String getFlawReason() {
		return flawReason;
	}

	/**
	 * Set the value related to the column: FLAW_REASON
	 *
	 * @param flawReason
	 *            the FLAW_REASON value
	 */
	public void setFlawReason(java.lang.String flawReason) {
		this.flawReason = flawReason;
	}

	/**
	 * Return the value associated with the column: ISSUE_FLAG
	 */
	public java.lang.String getIssueFlag() {
		return issueFlag;
	}

	/**
	 * Set the value related to the column: ISSUE_FLAG
	 *
	 * @param issueFlag
	 *            the ISSUE_FLAG value
	 */
	public void setIssueFlag(java.lang.String issueFlag) {
		this.issueFlag = issueFlag;
	}

	/**
	 * Return the value associated with the column: ISSUE_REASON
	 */
	public java.lang.String getIssueReason() {
		return issueReason;
	}

	/**
	 * Set the value related to the column: ISSUE_REASON
	 *
	 * @param issueReason
	 *            the ISSUE_REASON value
	 */
	public void setIssueReason(java.lang.String issueReason) {
		this.issueReason = issueReason;
	}

	/**
	 * Return the value associated with the column: OVERDUE_FLAG
	 */
	public java.lang.String getOverdueFlag() {
		return overdueFlag;
	}

	/**
	 * Set the value related to the column: OVERDUE_FLAG
	 *
	 * @param overdueFlag
	 *            the OVERDUE_FLAG value
	 */
	public void setOverdueFlag(java.lang.String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	/**
	 * Return the value associated with the column: OVERDUE_REASON
	 */
	public java.lang.String getOverdueReason() {
		return overdueReason;
	}

	/**
	 * Set the value related to the column: OVERDUE_REASON
	 *
	 * @param overdueReason
	 *            the OVERDUE_REASON value
	 */
	public void setOverdueReason(java.lang.String overdueReason) {
		this.overdueReason = overdueReason;
	}

	/**
	 * Return the value associated with the column: BAD_FLAG
	 */
	public java.lang.String getBadFlag() {
		return badFlag;
	}

	/**
	 * Set the value related to the column: BAD_FLAG
	 *
	 * @param badFlag
	 *            the BAD_FLAG value
	 */
	public void setBadFlag(java.lang.String badFlag) {
		this.badFlag = badFlag;
	}

	/**
	 * Return the value associated with the column: BAD_REASON
	 */
	public java.lang.String getBadReason() {
		return badReason;
	}

	/**
	 * Set the value related to the column: BAD_REASON
	 *
	 * @param badReason
	 *            the BAD_REASON value
	 */
	public void setBadReason(java.lang.String badReason) {
		this.badReason = badReason;
	}

	/**
	 * Return the value associated with the column: POOL_FLAG
	 */
	public java.lang.String getPoolFlag() {
		return poolFlag;
	}

	/**
	 * Set the value related to the column: POOL_FLAG
	 *
	 * @param poolFlag
	 *            the POOL_FLAG value
	 */
	public void setPoolFlag(java.lang.String poolFlag) {
		this.poolFlag = poolFlag;
	}

	/**
	 * Return the value associated with the column: OVERDUE_AMOUNT
	 */
	public java.math.BigDecimal getOverdueAmount() {
		return overdueAmount;
	}

	/**
	 * Set the value related to the column: OVERDUE_AMOUNT
	 *
	 * @param overdueAmount
	 *            the OVERDUE_AMOUNT value
	 */
	public void setOverdueAmount(java.math.BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	/**
	 * Return the value associated with the column: PLEDGE_STATUS
	 */
	public java.lang.String getPledgeStatus() {
		return pledgeStatus;
	}

	/**
	 * Set the value related to the column: PLEDGE_STATUS
	 *
	 * @param pledgeStatus
	 *            the PLEDGE_STATUS value
	 */
	public void setPledgeStatus(java.lang.String pledgeStatus) {
		this.pledgeStatus = pledgeStatus;
	}

	/**
	 * Return the value associated with the column: OVERDUE_STATUS
	 */
	public java.lang.String getOverdueStatus() {
		return overdueStatus;
	}

	/**
	 * Set the value related to the column: OVERDUE_STATUS
	 *
	 * @param overdueStatus
	 *            the OVERDUE_STATUS value
	 */
	public void setOverdueStatus(java.lang.String overdueStatus) {
		this.overdueStatus = overdueStatus;
	}

	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 *
	 * @param status
	 *            the STATUS value
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * Return the value associated with the column: RETURN_STATUS
	 */
	public java.lang.String getReturnStatus() {
		return returnStatus;
	}

	/**
	 * Set the value related to the column: RETURN_STATUS
	 *
	 * @param returnStatus
	 *            the RETURN_STATUS value
	 */
	public void setReturnStatus(java.lang.String returnStatus) {
		this.returnStatus = returnStatus;
	}

	/**
	 * Return the value associated with the column: PLEDGE_ACTIVE_DATE
	 */
	public java.util.Date getPledgeActiveDate() {
		return pledgeActiveDate;
	}

	/**
	 * Set the value related to the column: PLEDGE_ACTIVE_DATE
	 *
	 * @param pledgeActiveDate
	 *            the PLEDGE_ACTIVE_DATE value
	 */
	public void setPledgeActiveDate(java.util.Date pledgeActiveDate) {
		this.pledgeActiveDate = pledgeActiveDate;
	}

	/**
	 * Return the value associated with the column: UNPLEDGE_ACTIVE_DATE
	 */
	public java.util.Date getUnpledgeActiveDate() {
		return unpledgeActiveDate;
	}

	/**
	 * Set the value related to the column: UNPLEDGE_ACTIVE_DATE
	 *
	 * @param unpledgeActiveDate
	 *            the UNPLEDGE_ACTIVE_DATE value
	 */
	public void setUnpledgeActiveDate(java.util.Date unpledgeActiveDate) {
		this.unpledgeActiveDate = unpledgeActiveDate;
	}

	/**
	 * Return the value associated with the column: MEMO
	 */
	public java.lang.String getMemo() {
		return memo;
	}

	/**
	 * Set the value related to the column: MEMO
	 *
	 * @param memo
	 *            the MEMO value
	 */
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

	public Integer getPressCount() {
		return pressCount;
	}

	public void setPressCount(Integer pressCount) {
		this.pressCount = pressCount;
	}

	public Integer getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(Integer warnCount) {
		this.warnCount = warnCount;
	}

	/**
	 * Return the value associated with the column: BILLS_PERIOD
	 */
	public java.lang.String getBillsPeriod() {
		return billsPeriod;
	}

	/**
	 * Set the value related to the column: BILLS_PERIOD
	 *
	 * @param billsPeriod
	 *            the BILLS_PERIOD value
	 */
	public void setBillsPeriod(java.lang.String billsPeriod) {
		this.billsPeriod = billsPeriod;
	}

	/**
	 * Return the value associated with the column: BEGIN_DATE
	 */
	public java.util.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Set the value related to the column: BEGIN_DATE
	 *
	 * @param beginDate
	 *            the BEGIN_DATE value
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Return the value associated with the column: END_DATE
	 */
	public java.util.Date getEndDate() {
		return endDate;
	}

	/**
	 * Set the value related to the column: END_DATE
	 *
	 * @param endDate
	 *            the END_DATE value
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Return the value associated with the column: PAYDATE
	 */
	public java.util.Date getPaydate() {
		return paydate;
	}

	/**
	 * Set the value related to the column: PAYDATE
	 *
	 * @param paydate
	 *            the PAYDATE value
	 */
	public void setPaydate(java.util.Date paydate) {
		this.paydate = paydate;
	}

	/**
	 * Return the value associated with the column: LOAN_OVERDUE_FLAG
	 */
	public java.lang.String getLoanOverdueFlag() {
		return loanOverdueFlag;
	}

	/**
	 * Set the value related to the column: LOAN_OVERDUE_FLAG
	 *
	 * @param loanOverdueFlag
	 *            the LOAN_OVERDUE_FLAG value
	 */
	public void setLoanOverdueFlag(java.lang.String loanOverdueFlag) {
		this.loanOverdueFlag = loanOverdueFlag;
	}

	/**
	 * Return the value associated with the column: DELETE_REASON
	 */
	public java.lang.String getDeleteReason() {
		return deleteReason;
	}

	/**
	 * Set the value related to the column: DELETE_REASON
	 *
	 * @param deleteReason
	 *            the DELETE_REASON value
	 */
	public void setDeleteReason(java.lang.String deleteReason) {
		this.deleteReason = deleteReason;
	}

	/**
	 * Return the value associated with the column: OTHER_REASON
	 */
	public java.lang.String getOtherReason() {
		return otherReason;
	}

	/**
	 * Set the value related to the column: OTHER_REASON
	 *
	 * @param otherReason
	 *            the OTHER_REASON value
	 */
	public void setOtherReason(java.lang.String otherReason) {
		this.otherReason = otherReason;
	}

	/**
	 * Return the value associated with the column: REASON
	 */
	public java.lang.String getReason() {
		return reason;
	}

	/**
	 * Set the value related to the column: REASON
	 *
	 * @param reason
	 *            the REASON value
	 */
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}

	public java.math.BigDecimal getBailAmount() {
		return bailAmount;
	}

	public void setBailAmount(java.math.BigDecimal bailAmount) {
		this.bailAmount = bailAmount;
	}

	public java.math.BigDecimal getAddBailAmount() {
		return addBailAmount;
	}

	public void setAddBailAmount(java.math.BigDecimal addBailAmount) {
		this.addBailAmount = addBailAmount;
	}

	public java.lang.String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(java.lang.String isLocked) {
		this.isLocked = isLocked;
	}

	public java.lang.String getLockAppno() {
		return lockAppno;
	}

	public void setLockAppno(java.lang.String lockAppno) {
		this.lockAppno = lockAppno;
	}

	public java.lang.String getFactType() {
		return factType;
	}

	public void setFactType(java.lang.String factType) {
		this.factType = factType;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof TblDebtBaseInfo))
			return false;
		else {
			TblDebtBaseInfo tblDebtBaseInfo = (TblDebtBaseInfo) obj;
			if (null == this.getId() || null == tblDebtBaseInfo.getId())
				return false;
			else
				return (this.getId().equals(tblDebtBaseInfo.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

	/**
	 * @return the loanWay
	 */
	public java.lang.String getLoanWay() {
		return loanWay;
	}

	/**
	 * @param loanWay the loanWay to set
	 */
	public void setLoanWay(java.lang.String loanWay) {
		this.loanWay = loanWay;
	}

	public java.lang.String getBussContcode() {
		return bussContcode;
	}

	public void setBussContcode(java.lang.String bussContcode) {
		this.bussContcode = bussContcode;
	}

	public java.util.Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(java.util.Date deadLine) {
		this.deadLine = deadLine;
	}

	public java.lang.Integer getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(java.lang.Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public java.lang.String getDebtContno() {
		return debtContno;
	}
	public void setDebtContno(java.lang.String debtContno) {
		this.debtContno = debtContno;
	}

	private boolean select = false;

	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
}