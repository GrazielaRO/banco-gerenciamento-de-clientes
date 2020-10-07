package br.com.zup.estrelas.bancozup;

import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteCartaoException;
import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteChequeException;
import br.com.zup.estrelas.bancozup.exceptions.ExcedeLimiteQtdClientesException;
import br.com.zup.estrelas.bancozup.exceptions.ExcluiClienteException;
import br.com.zup.estrelas.bancozup.exceptions.SemSaldoException;

public class Gerente implements IControleLimite {
	private Cliente[] clientes;

	final int QTD_MAX_CLIENTES = 2;
	final double PORCENTAGEM_REDUCAO_LIMITE_CHEQUE = 0.75;
	final double PORCENTAGEM_REDUCAO_LIMITE_CARTAO = 0.80;

	public Gerente() {
		this.clientes = new Cliente[QTD_MAX_CLIENTES];
	}

	public boolean verificaExistenciaDaConta (String numeroConta) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes [i] != null && !clientes [i].getNumeroConta().equals(numeroConta)) {
				return true;
			}
		}
		return false;
	}
	
	public void cadastrarCliente(Cliente cliente) throws ExcedeLimiteQtdClientesException {
		for (int i = 0; i < this.clientes.length; i++) {
			if (this.clientes[i] == null) {
				this.clientes[i] = cliente;
				return;
			}
		}
		throw new ExcedeLimiteQtdClientesException(
				"\n\tALERTA!! A quantidade total de clientes foi ocupada com esse último cadastro.\n");
	}

	public boolean removerCliente(String numeroConta) throws ExcluiClienteException {
		// bancoDeDadosClientes();
		for (int i = 0; i < this.clientes.length; i++) {
			if (this.clientes[i] != null && this.clientes[i].getNumeroConta().equals(numeroConta)) {
				this.clientes[i] = null;
				return true;
			}
		}
		throw new ExcluiClienteException("\n\tNão foi possível remover. Cliente não existente em sitema\n");
	}

	@Override
	public boolean aumentarLimiteCheque(String numeroConta, double valorAumento)
			throws ExcedeAumentoLimiteChequeException {
		// bancoDeDadosClientes();
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroConta)
					&& valorAumento <= clientes[i].getLimiteChequeEspecial() * 0.5) {
				clientes[i].setLimiteChequeEspecial(clientes[i].getLimiteChequeEspecial() + valorAumento);
				return true;
			}
		}
		throw new ExcedeAumentoLimiteChequeException("\n\tO limite só pode ser aumentado em até 50%.\n");
	}

	@Override
	public boolean aumentarLimiteCartao(String numeroConta, double valorAumento)
			throws ExcedeAumentoLimiteCartaoException {
		// bancoDeDadosClientes();
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroConta)
					&& valorAumento <= clientes[i].getLimiteCartaoCredito() * 0.5) {
				clientes[i].setLimiteCartaoCredito(clientes[i].getLimiteCartaoCredito() + valorAumento);
				return true;
			}
		}
		throw new ExcedeAumentoLimiteCartaoException("\n\tO limite só pode ser aumentado em até 50%.\n");
	}

	@Override

	public boolean diminuirLimiteCheque(String numeroConta) {
		// bancoDeDadosClientes();
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroConta)) {
				clientes[i].setLimiteChequeEspecial(
						clientes[i].getLimiteChequeEspecial() * PORCENTAGEM_REDUCAO_LIMITE_CHEQUE);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean diminuirLimiteCartao(String numeroConta) {
		// bancoDeDadosClientes();
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroConta)) {
				clientes[i].setLimiteCartaoCredito(
						clientes[i].getLimiteCartaoCredito() * PORCENTAGEM_REDUCAO_LIMITE_CARTAO);
				return true;
			}
		}
		return false;
	}

	public boolean transferencia(String numeroContaOrigem, String numeroContaDestino, double valorTransferencia)
			throws SemSaldoException {

		// bancoDeDadosClientes();
		int aux = 0;
		int contaDestinoExistente = 0, contaOrigemExistente = 0;

		if (!numeroContaOrigem.equals(numeroContaDestino)) {
			// Debitando Saldo da conta origem;
			for (int i = 0; i < clientes.length; i++) {
				if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroContaOrigem)) {
					if (clientes[i].getSaldo() >= valorTransferencia) {
						clientes[i].setSaldo(clientes[i].getSaldo() - valorTransferencia);
						contaOrigemExistente = 1;
						aux = i;
						break;
					}
				}
			}

			// Creditando Saldo na Conta Destino;
			if (contaOrigemExistente == 1) {
				for (int i = 0; i < clientes.length; i++) {
					if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroContaDestino)) {
						clientes[i].setSaldo(clientes[i].getSaldo() + valorTransferencia);
						contaDestinoExistente = 1;
					}
				}
			}

			if (contaOrigemExistente == 1 && contaDestinoExistente != 1) {
				clientes[aux].setSaldo(clientes[aux].getSaldo() + valorTransferencia);
				return false;
			}
			if (contaOrigemExistente == 1 && contaDestinoExistente == 1) {
				return true;
			} else {
				throw new SemSaldoException("\n\tSaldo insuficiente para realizar a transferência.\n");
			}

		}

		return false;
	}

	public boolean adicionaSaldo(String numeroConta, double deposito) {

		// bancoDeDadosClientes();
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getNumeroConta().equals(numeroConta)) {
				clientes[i].setSaldo(clientes[i].getSaldo() + deposito);
				return true;
			}
		}
		return false;
	}

	public void imprimeClientes() {
		for (Cliente cliente : clientes) {
			if (cliente != null) {
				/*
				 * if(cliente instanceof PessoaFisica) { PessoaFisica pessoaFisica =
				 * (PessoaFisica) cliente; pessoaFisica.imprimeDados(); }
				 */

				cliente.imprimeDados();
			}
		}
	}
}