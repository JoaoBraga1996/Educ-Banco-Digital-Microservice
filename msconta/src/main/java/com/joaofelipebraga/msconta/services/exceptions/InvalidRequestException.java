package com.joaofelipebraga.msconta.services.exceptions;

public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidRequestException(String mensagem) {
		super(mensagem);
	}

}
