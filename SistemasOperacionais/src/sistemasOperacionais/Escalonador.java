package sistemasOperacionais;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Escalonador {

	private List<Processo> listaProcessos;

	public void adicionarProcesso(Processo processo) {
		if (listaProcessos == null) {
			listaProcessos = new ArrayList<>();
		}
		listaProcessos.add(processo);
	}

	public void FCFS() {
		if (listaProcessos.isEmpty()) {
			System.out.println("Não há processos na lista.");
			return;
		}

		int tempoTotalEspera = 0;
		System.out.println("Executando FCFS:");
		for (Processo processo : listaProcessos) {
			System.out.println("\nExecutando processo ID " + processo.getId());

			int tempoEspera = tempoTotalEspera;
			tempoTotalEspera += processo.getTempoExecucao();

			System.out.println("Processo ID " + processo.getId() + " executado.");
			System.out.println("Tempo de espera: " + tempoEspera);
		}

		double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
		System.out.println("Tempo médio de espera: " + tempoMedioEspera);

	}

	public void SJF_NaoPreemptivo() {
		if (listaProcessos.isEmpty()) {
			System.out.println("Não há processos na lista.");
			return;
		}

		listaProcessos.sort(Comparator.comparingInt(Processo::getTempoExecucao));

		int tempoTotalEspera = 0;
		System.out.println("Executando SJF Não-Preemptivo (Shortest Job First):");
		for (Processo processo : listaProcessos) {
			System.out.println("\nExecutando processo ID " + processo.getId());

			int tempoEspera = tempoTotalEspera;
			tempoTotalEspera += processo.getTempoExecucao();

			System.out.println("Processo ID " + processo.getId() + " executado.");
			System.out.println("Tempo de espera: " + tempoEspera);
		}

		double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
		System.out.println("Tempo médio de espera: " + tempoMedioEspera);
	}

	public void SJF_Preemptivo() {
		if (listaProcessos.isEmpty()) {
			System.out.println("Não há processos na lista.");
			return;
		}

		PriorityQueue<Processo> fila = new PriorityQueue<>(Comparator.comparingInt(Processo::getTempoExecucao));
		int tempoAtual = 0;
		int tempoTotalEspera = 0;

		while (!listaProcessos.isEmpty() || !fila.isEmpty()) {
			while (!listaProcessos.isEmpty() && listaProcessos.get(0).getTempoChegada() <= tempoAtual) {
				fila.add(listaProcessos.remove(0));
			}

			if (fila.isEmpty()) {
				tempoAtual = listaProcessos.get(0).getTempoChegada();
				continue;
			}

			Processo processoAtual = fila.poll();
			System.out.println("\nExecutando processo ID " + processoAtual.getId() + " no tempo " + tempoAtual);

			int tempoEspera = tempoAtual;
			tempoTotalEspera += tempoEspera;

			tempoAtual += processoAtual.getTempoExecucao();

			System.out.println("Processo ID " + processoAtual.getId() + " executado.");
			System.out.println("Tempo de espera: " + tempoEspera);
		}

		double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
		System.out.println("Tempo médio de espera: " + tempoMedioEspera);
	}

	public void Prioridade_NaoPreemptivo() {
		if (listaProcessos.isEmpty()) {
			System.out.println("Não há processos na lista.");
			return;
		}

		listaProcessos.sort(Comparator.comparingInt(Processo::getPrioridade));

		int tempoTotalEspera = 0;
		System.out.println("Executando Prioridade Não-Preemptivo:");

		for (Processo processo : listaProcessos) {
			System.out.println(
					"\nExecutando processo ID " + processo.getId() + " com prioridade " + processo.getPrioridade());

			int tempoEspera = tempoTotalEspera;
			tempoTotalEspera += processo.getTempoExecucao();

			System.out.println("Processo ID " + processo.getId() + " executado.");
			System.out.println("Tempo de espera: " + tempoEspera);
		}

		double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
		System.out.println("Tempo médio de espera: " + tempoMedioEspera);
	}

	public void Prioridade_Preemptivo() {
		if (listaProcessos.isEmpty()) {
			System.out.println("Não há processos na lista.");
			return;
		}

		PriorityQueue<Processo> fila = new PriorityQueue<>(Comparator.comparingInt(Processo::getPrioridade));
		int tempoAtual = 0;
		int tempoTotalEspera = 0;

		while (!listaProcessos.isEmpty() || !fila.isEmpty()) {
			while (!listaProcessos.isEmpty() && listaProcessos.get(0).getTempoChegada() <= tempoAtual) {
				fila.add(listaProcessos.remove(0));
			}

			if (fila.isEmpty()) {
				tempoAtual = listaProcessos.get(0).getTempoChegada();
				continue;
			}

			Processo processoAtual = fila.poll();
			System.out.println("\nExecutando processo ID " + processoAtual.getId() + " no tempo " + tempoAtual);

			int tempoEspera = tempoAtual;
			tempoTotalEspera += tempoEspera;

			tempoAtual += processoAtual.getTempoExecucao();

			System.out.println("Processo ID " + processoAtual.getId() + " executado.");
			System.out.println("Tempo de espera: " + tempoEspera);
		}

		double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
		System.out.println("Tempo médio de espera: " + tempoMedioEspera);
	}

	public void RoundRobin(int quantum) {
		 if (listaProcessos.isEmpty()) {
	            System.out.println("Não há processos na lista.");
	            return;
	        }

	        Queue<Processo> fila = new LinkedList<>(listaProcessos);
	        int tempoTotalEspera = 0;
	        int tempoAtual = 0;

	        System.out.println("Executando Round-Robin com quantum de " + quantum + ":");

	        while (!fila.isEmpty()) {
	            Processo processoAtual = fila.poll();
	            System.out.println("\nExecutando processo ID " + processoAtual.getId() + " no tempo " + tempoAtual);

	            int tempoEspera = tempoAtual;
	            if (processoAtual.getTempoExecucao() > quantum) {
	                tempoAtual += quantum;
	                processoAtual.setTempoExecucao(processoAtual.getTempoExecucao() - quantum);
	                fila.add(processoAtual);
	            } else {
	                tempoAtual += processoAtual.getTempoExecucao();
	                tempoTotalEspera += tempoEspera;
	                System.out.println("Processo ID " + processoAtual.getId() + " executado.");
	                System.out.println("Tempo de espera: " + tempoEspera);
	            }
	        }

	        double tempoMedioEspera = (double) tempoTotalEspera / listaProcessos.size();
	        System.out.println("Tempo médio de espera: " + tempoMedioEspera);
	}

	public void mostrarResultado() {
		 if (listaProcessos.isEmpty()) {
	            System.out.println("Não há processos na lista.");
	            return;
	        }

	        System.out.println("Resultados do escalonamento:");

	        for (Processo processo : listaProcessos) {
	            System.out.println("Processo " + processo.getId() + ":");
	            System.out.println("Tempo Execucao = " + processo.getTempoExecucao());
	            System.out.println("Tempo Restante = " + processo.getTempoExecucao());
	            System.out.println("Tempo Chegada = " + processo.getTempoChegada());
	            System.out.println("Prioridade = " + processo.getPrioridade());
	            System.out.println();
	        }
	}

	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}

}
