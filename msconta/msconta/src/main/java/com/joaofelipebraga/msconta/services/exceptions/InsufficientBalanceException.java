package com.joaofelipebraga.msconta.services.exceptions;

public class InsufficientBalanceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String mensagem) {
		super(mensagem);
	}

}
