package com.joaofelipebraga.msconta.services.exceptions;

public class SameAccountsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SameAccountsException(String mensagem) {
		super(mensagem);
	}

}
