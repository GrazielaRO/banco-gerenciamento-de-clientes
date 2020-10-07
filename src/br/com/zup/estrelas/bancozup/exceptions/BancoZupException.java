package br.com.zup.estrelas.bancozup.exceptions;

public class BancoZupException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8591396175836892742L;
	
	private String mensagem;
	
	public BancoZupException (String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
