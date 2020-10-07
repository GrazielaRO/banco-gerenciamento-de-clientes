package br.com.zup.estrelas.bancozup.exceptions;

public class ExcedeAumentoLimiteChequeException extends BancoZupException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178731950075229396L;
	
	private String mensagem;

	public ExcedeAumentoLimiteChequeException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
