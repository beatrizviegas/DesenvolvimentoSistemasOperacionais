package sistemasOperacionais;

import java.util.Scanner;

public class Main {

	static Scanner key = new Scanner(System.in);
	private static Processo manuais;
	private static Processo processo;
	static int modoDeEntrada;
	static int numeroProcessos;
	static int tempoChegada;
	static int tempoExecucao;
	static int tempoRestante;
	static int prioridade;
	static int opcao = 0;
	static int slice;

	public static void main(String[] args) {
		System.out.println("para inicializar os processo digite a quantidade de processo ou 0 para cancelar");
		numeroProcessos = key.nextInt();
		if (numeroProcessos == 0) {
			msgCancelarOperacao();
		} else {

			System.out.println("Escolha o modo de entrada para os processos:" + "\n[1] Automático" + "\n[2] Manual"
					+ "\n[0] Sair");
			modoDeEntrada = key.nextInt();

			if (modoDeEntrada == 2) {
				manual(tempoChegada, tempoExecucao, prioridade);
			} else if (modoDeEntrada == 0) {
				msgCancelarOperacao();
				opcao = 0;
			} else {
				for (int i = 0; i < numeroProcessos; i++) {
					processo = new Processo(i);
				}
			}
			escalonador();
		}
	}

	public static void msgCancelarOperacao() {
		System.out.println("operação cancelada!!");
	}

	private static void manual(int tempoChegada, int tempoExecucao, int prioridade) {
		for (int i = 0; i < numeroProcessos; i++) {
			System.out.println("Digite o tempo de chegada do processo [" + i + "]:");
			tempoChegada = key.nextInt();
			System.out.println("Digite o tempo de execução do processo [" + i + "]:");
			tempoExecucao = key.nextInt();
			System.out.println("Digite a prioridade do processo [" + i + "]:");
			prioridade = key.nextInt();
			manuais = new Processo(i, tempoChegada, tempoExecucao, prioridade);
		}
	}

	private static void escalonador() {
		Escalonador e = new Escalonador();
		System.out.println("\n=====================================");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Executar FCFS");
		System.out.println("2. Executar SJF Não-Preemptivo");
		System.out.println("3. Executar SJF Preemptivo");
		System.out.println("4. Executar Prioridade Não-Preemptivo");
		System.out.println("5. Executar Prioridade Preemptivo");
		System.out.println("6. Executar Round-Robin");
		System.out.println("7. Exibir resultados");
		System.out.println("8. Popular processos automaticamente");
		System.out.println("0. Sair");
		System.out.println("=====================================\n");

		opcao = key.nextInt();
		do {
			switch (opcao) {
			case 1:
				e.FCFS();
				break;
			case 2:
				e.SJFNaoPreemptivo();
				break;
			case 3:
				e.SJFPreemptivo();
				break;
			case 4:
				e.PrioridadeNaoPreemptivo();
				break;
			case 5:
				e.PrioridadePreemptivo();
				break;
			case 6:
				System.out.println("Digite o Time-Slice: ");
				slice = key.nextInt();
				e.RoundRobin(slice);
				break;
			case 7:
				e.info();
				break;
			case 8:
				Processo deleta = new Processo();
				deleta.deletaArrayProcessos();
				System.out.println("Digite a quantidade de processos para execução:");
				numeroProcessos = key.nextInt();

				System.out.println("Escolha o modo de entrada para os processos:" + "\n[1] Automático" + "\n[2] Manual"
						+ "\n[0] Sair");
				modoDeEntrada = key.nextInt();

				if (modoDeEntrada == 1) {
					manual(tempoChegada, tempoExecucao, prioridade);
				} else {
					for (int i = 0; i < numeroProcessos; i++) {
						processo = new Processo(i);
					}
				}
			case 0:
				msgCancelarOperacao();
				break;

			default:
				System.out.println("Opção inválida. Escolha novamente.");
				break;
			}
		} while (opcao != 0);
	}
}