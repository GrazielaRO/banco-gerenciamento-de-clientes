package br.com.zup.estrelas.bancozup;

import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteCartaoException;
import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteChequeException;

public interface IControleLimite {
	boolean aumentarLimiteCheque(String nomeConta, double valorAumento) throws ExcedeAumentoLimiteChequeException;
	
	boolean aumentarLimiteCartao(String nomeConta, double valorAumento) throws ExcedeAumentoLimiteCartaoException;
	
	boolean diminuirLimiteCheque(String nomeConta);
	
	boolean diminuirLimiteCartao(String nomeConta);
}