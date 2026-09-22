package com.javarush.mashin.projectjstl.exception;

public class ProjectJSTLException extends RuntimeException{
	public ProjectJSTLException() {
		super();
	}

	public ProjectJSTLException(Throwable cause) {
		super(cause);
	}

	public ProjectJSTLException(String message) {
		super(message);
	}

	public ProjectJSTLException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ProjectJSTLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
