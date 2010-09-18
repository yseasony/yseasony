package org.yseasony.blog.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException,从被Spring声明式事务管理的函数中抛出时会触发事务回滚.
 * 
 * @author calvin
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable cause) {
		super(cause);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
}
