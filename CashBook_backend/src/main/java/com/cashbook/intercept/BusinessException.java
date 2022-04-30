

package com.cashbook.intercept;


import com.cashbook.common.Meta;

public class BusinessException extends RuntimeException {


	private static final long serialVersionUID = 5565760508056698922L;

	private Meta errorCode;

	public BusinessException(Meta errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BusinessException(Meta errorCode, String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.errorCode = errorCode;
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BusinessException(Meta errorCode, String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errorCode = errorCode;
	}

	public BusinessException(String arg0) {
		super(arg0);
	}

	public BusinessException(Meta errorCode, String arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public BusinessException(Throwable arg0) {
		super(arg0);
	}

	public BusinessException(Meta errorCode, Throwable arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public Meta getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Meta errorCode) {
		this.errorCode = errorCode;
	}

}
