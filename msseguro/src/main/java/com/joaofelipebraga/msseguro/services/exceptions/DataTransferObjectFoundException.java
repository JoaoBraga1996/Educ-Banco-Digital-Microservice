package com.joaofelipebraga.msseguro.services.exceptions;

public class DataTransferObjectFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataTransferObjectFoundException(String mensagem) {
		super(mensagem);
	}

}
