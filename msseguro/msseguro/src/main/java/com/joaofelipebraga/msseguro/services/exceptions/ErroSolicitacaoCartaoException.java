package com.joaofelipebraga.msseguro.services.exceptions;

public class ErroSolicitacaoCartaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErroSolicitacaoCartaoException(String mensagem) {
		super(mensagem);
	}

}
