package com.hcl.retailbanking.exception;

import com.hcl.retailbanking.util.StringConstant;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return StringConstant.ACCOUNT_NOT_FOUND;
	}

}
