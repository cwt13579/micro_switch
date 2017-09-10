package com.tx.cmd.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.tx.cmd.core.BaseDAO;
import com.tx.cmd.data.TblDebtBaseInfo;
@Repository
public class TblDebtBaseInfoDAO extends BaseDAO<TblDebtBaseInfo> {
	private static final Log log = LogFactory.getLog(TblDebtBaseInfoDAO.class);
	@Override
	protected Class<TblDebtBaseInfo> getReferenceClass() {
		return TblDebtBaseInfo.class;
	}

	public TblDebtBaseInfoDAO() {
	}

	public TblDebtBaseInfo get(String id) {
		return this.get(getReferenceClass(), id);
	}
	

}