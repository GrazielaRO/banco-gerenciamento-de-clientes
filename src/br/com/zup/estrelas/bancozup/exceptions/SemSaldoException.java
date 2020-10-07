package br.com.zup.estrelas.bancozup.exceptions;

public class SemSaldoException extends BancoZupException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9035334221029825946L;
	
	private String message;

	public SemSaldoException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
