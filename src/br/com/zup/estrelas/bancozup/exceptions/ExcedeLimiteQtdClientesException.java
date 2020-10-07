package br.com.zup.estrelas.bancozup.exceptions;

public class ExcedeLimiteQtdClientesException extends BancoZupException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4029226235265818532L;
	private String mensagem;

	public ExcedeLimiteQtdClientesException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
