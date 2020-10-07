package br.com.zup.estrelas.bancozup.exceptions;

public class ExcedeAumentoLimiteCartaoException extends BancoZupException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8292350670193923728L;
	
	private String mensagem;

	public ExcedeAumentoLimiteCartaoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
