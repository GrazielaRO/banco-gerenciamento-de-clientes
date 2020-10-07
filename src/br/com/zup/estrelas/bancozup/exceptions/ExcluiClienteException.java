package br.com.zup.estrelas.bancozup.exceptions;

public class ExcluiClienteException extends BancoZupException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2434229681073525753L;
	
	private String msg;

	public ExcluiClienteException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
