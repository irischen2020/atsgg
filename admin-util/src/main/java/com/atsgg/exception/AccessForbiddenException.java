package com.atsgg.exception;

/**
 * 表示用户没有登录时就访问受保护的资源抛出的异常
 */
public class AccessForbiddenException extends RuntimeException {
	public AccessForbiddenException() {
		super();
	}
	
	public AccessForbiddenException(String message) {
		super(message);
	}
	
	public AccessForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AccessForbiddenException(Throwable cause) {
		super(cause);
	}
	
	protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
