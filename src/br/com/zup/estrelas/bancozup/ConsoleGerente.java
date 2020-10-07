package br.com.zup.estrelas.bancozup;

import java.util.Scanner;

import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteCartaoException;
import br.com.zup.estrelas.bancozup.exceptions.ExcedeAumentoLimiteChequeException;
import br.com.zup.estrelas.bancozup.exceptions.ExcedeLimiteQtdClientesException;
import br.com.zup.estrelas.bancozup.exceptions.ExcluiClienteException;
import br.com.zup.estrelas.bancozup.exceptions.SemSaldoException;

public class ConsoleGerente {

	final static String MENU_PRINCIPAL = ("\t# Sistema De Gerenciamento de Clientes #\n\n")
			+ ("\t(1) - Cadastrar novo cliente;\n") + ("\t(2) - Remover cliente;\n")
			+ ("\t(3) - Aumentar limite cartão;\n") + ("\t(4) - Diminuir limite cartão;\n")
			+ ("\t(5) - Aumentar limite cheque especial;\n") + ("\t(6) - Diminuir limite cheque especial;\n")
			+ ("\t(7) - Realizar transferência;\n") + ("\t(8) - Realizar depósito;\n")
			+ ("\t(9) - Imprimir clientes;\n") + ("\t(0) - Finalizar programa;\n");

	final static String MENU_SECUNDARIO = ("\n\t(1) - Cadastrar pessoa física\n")
			+ ("\t(2) - Cadastrar pessoa jurídica\n") + ("\t(0) - Ir para o menu principal\n");

	public static void menuCadastrarCliente(Scanner teclado, Gerente gerente) {

		String opcao = null;

		do {
			System.out.println(MENU_SECUNDARIO);
			System.out.print("\n\tEscolha uma das opções acima: ");
			opcao = teclado.next();

			switch (opcao) {
			case "1":

				adicionarPessoaFisica(teclado, gerente);

				break;

			case "2":

				adicionarPessoaJuridica(teclado, gerente);

				break;

			case "0":
				break;

			default:
				System.out.println("\n\tOpção inválida. Por favor tente uma opção de 0 a 3.\n");
				break;
			}

		} while (!opcao.equals("0"));
	}

	public static void adicionarPessoaFisica(Scanner teclado, Gerente gerente) {
		teclado.nextLine();
		System.out.print("\n\tDigite o nome do cliente: ");
		String nome = teclado.nextLine();

		System.out.print("\n\tDigite o CPF: ");
		String cpf = teclado.next();

		System.out.print("\n\tDigite a idade: ");
		int idade = teclado.nextInt();

		System.out.print("\n\tDigite o telefone do cliente: ");
		String telefone = teclado.next();

		System.out.print("\n\tDigite o numero da agencia: ");
		String agencia = teclado.next();

		System.out.print("\n\tDigite o numero da conta: ");
		String numeroDaConta = teclado.next();

		System.out.print("\n\tDigite o saldo inicial: ");
		double saldo = teclado.nextDouble();

		System.out.print("\n\tDigite o limite do cheque especial: ");
		double limiteChequeEspecial = teclado.nextDouble();

		System.out.print("\n\tDigite o limite do cartão de crédito: ");
		double limiteCartaoCredito = teclado.nextDouble();

		PessoaFisica pf = new PessoaFisica(numeroDaConta, telefone, agencia, saldo, limiteChequeEspecial,
				limiteCartaoCredito, cpf, nome, idade);

		try {
			gerente.cadastrarCliente(pf);
			System.out.println("\n\tCliente cadastrado com sucesso!\n");
		} catch (ExcedeLimiteQtdClientesException e) {
			System.out.println(e.getMensagem());
		}

	}

	public static void adicionarPessoaJuridica(Scanner teclado, Gerente gerente) {
		teclado.nextLine();
		System.out.print("\n\tDigite a razão social: ");
		String razaoSocial = teclado.nextLine();

		System.out.print("\n\tDigite o nome fantasia: ");
		String nomeFantasia = teclado.nextLine();

		System.out.print("\n\tDigite o CNPJ: ");
		String cnpj = teclado.nextLine();

		System.out.print("\n\tDigite o telefone do cliente: ");
		String telefone = teclado.next();

		System.out.print("\n\tDigite o numero da agencia: ");
		String agencia = teclado.next();

		System.out.print("\n\tDigite o numero da conta: ");
		String numeroDaConta = teclado.next();

		System.out.print("\n\tDigite o saldo inicial: ");
		double saldo = teclado.nextDouble();

		System.out.print("\n\tDigite o limite do cheque especial: ");
		double limiteChequeEspecial = teclado.nextDouble();

		System.out.print("\n\tDigite o limite do cartão de crédito: ");
		double limiteCartaoCredito = teclado.nextDouble();
		int qtdSocios = 0;

		do {
			System.out.print("\n\tQuantos socios deseja cadastrar: ");
			qtdSocios = teclado.nextInt();
			if (qtdSocios <= 0 && qtdSocios >= 4) {
				System.out.println("\n\tInclua de 1 a 3 sócios/\n");
			}
			teclado.nextLine();
		} while (qtdSocios <= 0 && qtdSocios >= 4);

		String[] nomesSocios = new String[qtdSocios];
		for (int i = 0; i < nomesSocios.length; i++) {
			System.out.printf("\n\tDigite o %d° nome: ", i + 1);
			nomesSocios[i] = teclado.nextLine();
		}

		PessoaJuridica pj = new PessoaJuridica(numeroDaConta, telefone, agencia, saldo, limiteChequeEspecial,
				limiteCartaoCredito, cnpj, razaoSocial, nomeFantasia, nomesSocios);

		try {
			gerente.cadastrarCliente(pj);
		} catch (ExcedeLimiteQtdClientesException e) {
			System.out.println(e.getMensagem());
		}
	}

	public static void deletarConta(Gerente gerente, String numeroConta) {
		try {
			gerente.removerCliente(numeroConta);
			System.out.println("\n\tConta excluida com sucesso!\n");
		} catch (ExcluiClienteException e) {
			System.out.println(e.getMsg());
		}
	}

	public static void aumentarLimiteCartao(Gerente gerente, String numeroConta, double valorDoAumento) {
		try {
			gerente.aumentarLimiteCartao(numeroConta, valorDoAumento);
			System.out.println("\n\tLimite aumentado com sucesso!\n");
		} catch (ExcedeAumentoLimiteCartaoException e2) {
			System.out.println(e2.getMensagem());
		}
	}

	public static void reduzirLimiteCartao(Gerente gerente, String numeroConta) {
		if (gerente.diminuirLimiteCartao(numeroConta)) {
			System.out.println("\n\tLimite reduzido com sucesso!\n");
		} else {
			System.out.println("\n\tNúmero da conta inexistente!\n");
		}
	}

	public static void aumentarLimiteChequeEspecial(Gerente gerente, String numeroConta, double valorAumento) {
		try {
			gerente.aumentarLimiteCheque(numeroConta, valorAumento);
			System.out.println("\n\tLimite aumentado com sucesso!\n");
		} catch (ExcedeAumentoLimiteChequeException e1) {
			System.out.println(e1.getMensagem());
		}
	}

	public static void reduzirLimiteChequeEspecial(Gerente gerente, String numeroConta) {
		if (gerente.diminuirLimiteCheque(numeroConta)) {
			System.out.println("\n\tLimite reduzido com sucesso!\n");
		} else {
			System.out.println("\n\tNúmero da conta inexistente!\n");
		}
	}

	public static void realizarTransferencia(Gerente gerente, String numeroContaOrigem, String numeroContaDestino,
			double valorTransferencia) {
		try {
			gerente.transferencia(numeroContaOrigem, numeroContaDestino, valorTransferencia);
			System.out.println("\n\tTransferência realizada com sucesso!\n");
		} catch (SemSaldoException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void realizarDeposito(Gerente gerente, String numeroContaDestino, Double deposito) {

		if (gerente.adicionaSaldo(numeroContaDestino, deposito)) {
			System.out.println("\n\tDeposito realizado com sucesso!\n");
		} else {
			System.out.println("\n\tDeposito não realizado!\n");
		}
	}

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Gerente gerente = new Gerente();

		String opcao = null;

		do {
			System.out.println(MENU_PRINCIPAL);
			System.out.print("\n\n\tEscolha uma das opções acima: ");
			opcao = teclado.next();

			switch (opcao) {
			case "1":
				menuCadastrarCliente(teclado, gerente);

				break;

			case "2":
				System.out.print("\n\tDigite o número da conta que deseja excluir: ");
				String numeroConta = teclado.next();

				deletarConta(gerente, numeroConta);

				break;

			case "3":
				System.out.print("\n\tDigite o número da conta que deseja aumentar o limite: ");
				numeroConta = teclado.next();
				System.out.print("\n\tDigite o valor a ser acrescentado ao limite: ");
				double valorDoAumento = teclado.nextDouble();

				aumentarLimiteCartao(gerente, numeroConta, valorDoAumento);

				break;

			case "4":
				System.out.print("\n\tDigite o número da conta que deseja reduzir o limite: ");
				numeroConta = teclado.next();

				reduzirLimiteCartao(gerente, numeroConta);

				break;

			case "5":
				System.out.print("\n\tDigite o número da conta que deseja aumentar o limite: ");
				numeroConta = teclado.next();
				System.out.print("\n\tDigite o valor a ser acrescentado ao limite: ");
				double valorAumento = teclado.nextDouble();

				aumentarLimiteChequeEspecial(gerente, numeroConta, valorAumento);

				break;

			case "6":
				System.out.print("\n\tDigite o número da conta que deseja reduzir o limite: ");
				numeroConta = teclado.next();

				reduzirLimiteChequeEspecial(gerente, numeroConta);

				break;

			case "7":
				System.out.print("\n\tDigite o número da conta de origem: ");
				String numeroContaOrigem = teclado.next();

				System.out.print("\n\tDigite o número da conta de destino: ");
				String numeroContaDestino = teclado.next();

				System.out.print("\n\tDigite o valor a ser transferido: R$ ");
				double valorTransferencia = teclado.nextDouble();

				realizarTransferencia(gerente, numeroContaOrigem, numeroContaDestino, valorTransferencia);

				break;

			case "8":

				System.out.print("\n\tDigite o número da conta de destino: ");
				numeroContaDestino = teclado.next();

				System.out.print("\n\tDigite o valor a ser depositado: ");
				double deposito = teclado.nextDouble();

				realizarDeposito(gerente, numeroContaDestino, deposito);

				break;

			case "9":
				gerente.imprimeClientes();

				break;

			case "0":
				System.out.println("\n\tObrigado por usar o nosso sistema!\n");

				break;

			default:
				System.out.println("\n\tOpção inválida! Escolha uma opção de 0 a 9.\n");

				break;
			}

		} while (!opcao.equals("0"));
		teclado.close();
	}
}
