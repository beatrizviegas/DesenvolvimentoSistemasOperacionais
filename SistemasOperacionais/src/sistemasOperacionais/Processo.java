package sistemasOperacionais;

import java.util.ArrayList;
import java.util.Random;

public class Processo {
	private int processo;
	private int tempoChegada;
	private int tempoExecucao;

	private int tempoRestante;
	private int prioridade;

	private int tempoEspera;
	public ArrayList<Processo> arrayProcessos = new ArrayList<>();

	public Processo(int processo, int tempoChegada, int tempoExecucao, int prioridade) {
		this.processo = processo;
		this.tempoChegada = tempoChegada;
		this.tempoExecucao = tempoExecucao;
		this.tempoRestante = tempoExecucao;
		this.prioridade = prioridade;
		this.tempoEspera = 0;

		arrayProcessos.add(this);
	}

	public Processo(int processo) {
		Random rand = new Random();
		this.processo = processo;
		this.tempoChegada = rand.nextInt(10) + 1;
		this.tempoExecucao = rand.nextInt(10) + 1;
		this.tempoRestante = tempoExecucao;
		this.prioridade = rand.nextInt(10) + 1;
		this.tempoEspera = 0;

		arrayProcessos.add(this);
	}

	public Processo() {

	}

	public ArrayList<Processo> escalonamento() {

		ArrayList<Processo> processo = new ArrayList<>();

		for (int i = 0; i < arrayProcessos.size(); i++) {
			Processo p = new Processo();
			p.setProcesso(arrayProcessos.get(i).getProcesso());
			p.setPrioridade(arrayProcessos.get(i).getPrioridade());
			p.setTempoChegada(arrayProcessos.get(i).getTempoChegada());
			p.setTempoExecucao(arrayProcessos.get(i).getTempoExecucao());
			p.setTempoRestante(arrayProcessos.get(i).getTempoRestante());

			processo.add(p);
		}

		return processo;
	}

	public void info() {
		for (int i = 0; i < arrayProcessos.size(); i++) {
			System.out.println("Processo: " + arrayProcessos.get(i).getProcesso());
			System.out.println("Tempo chegada: " + arrayProcessos.get(i).getTempoChegada());
			System.out.println("Tempo execução: " + arrayProcessos.get(i).getTempoExecucao());
			System.out.println("Tempo restante: " + arrayProcessos.get(i).getTempoRestante());
			System.out.println("Prioridade: " + arrayProcessos.get(i).getPrioridade());
			System.out.println(" ");
		}

	}

	public Processo procuraProcessoChegada() {
		Processo menorTempoChegada = new Processo();
		menorTempoChegada.setTempoChegada(999);

		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).getTempoChegada() < menorTempoChegada.getTempoChegada()) {
				if (arrayProcessos.get(i).getTempoRestante() > 0) {
					menorTempoChegada = arrayProcessos.get(i);
				}
			}
		}

		return menorTempoChegada;
	}

	public void ajustaTempoRestante(Processo processo) {
		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).equals(processo)) {
				arrayProcessos.get(i).setTempoRestante(processo.getTempoRestante());
			}
		}
	}

	public void reiniciaTempoRestante(Processo processo) {
		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).getProcesso() == processo.getProcesso()) {
				arrayProcessos.get(i).setTempoRestante(processo.getTempoExecucao());
			}
		}
	}

	public Processo procuraProcessoExecucao() {
		Processo menorTempoExecucao = new Processo();
		menorTempoExecucao.setTempoExecucao(999);
		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).getTempoExecucao() < menorTempoExecucao.getTempoExecucao()) {
				if (arrayProcessos.get(i).getTempoRestante() > 0) {
					menorTempoExecucao = arrayProcessos.get(i);
				}
			}
		}

		return menorTempoExecucao;
	}

	public Processo procuraProcessoExecucaoRestante(int tempoExecucao) {
		Processo menorTempoRestante = new Processo();
		menorTempoRestante.setTempoRestante(999);
		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).getTempoRestante() < menorTempoRestante.getTempoRestante()) {
				if (arrayProcessos.get(i).getTempoChegada() <= tempoExecucao) {
					if (arrayProcessos.get(i).getTempoRestante() > 0) {
						menorTempoRestante = arrayProcessos.get(i);
					}
				}
			}
		}
		if (menorTempoRestante.getTempoRestante() == 999) {
			menorTempoRestante.setTempoRestante(0);
			return menorTempoRestante;

		} else {
			return menorTempoRestante;
		}
	}

	public boolean verificaTempoExecucao(Processo processos, int tempoExecucaoProcesso) {
		if (processos.getTempoChegada() <= tempoExecucaoProcesso) {
			return true;
		} else {
			return false;
		}
	}

	public Processo procuraProcessoPrioridade(int tempoExecucao) {
		Processo maiorPrioridade = new Processo();
		maiorPrioridade.setPrioridade(999);
		for (int i = 0; i < arrayProcessos.size(); i++) {
			if (arrayProcessos.get(i).getPrioridade() < maiorPrioridade.getPrioridade()) {
				if (arrayProcessos.get(i).getTempoChegada() <= tempoExecucao) {
					if (arrayProcessos.get(i).getTempoRestante() > 0) {
						maiorPrioridade = arrayProcessos.get(i);
					}
				}
			}
		}
		if (maiorPrioridade.getPrioridade() == 999) {
			maiorPrioridade.setTempoRestante(0);
			return maiorPrioridade;

		} else {
			return maiorPrioridade;
		}
	}

	public void deletaArrayProcessos() {
		arrayProcessos.clear();
	}

	public int getProcesso() {
		return processo;
	}

	public void setProcesso(int processo) {
		this.processo = processo;
	}

	public int getTempoChegada() {
		return tempoChegada;
	}

	public void setTempoChegada(int tempoChegada) {
		this.tempoChegada = tempoChegada;
	}

	public int getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(int tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public int getTempoRestante() {
		return tempoRestante;
	}

	public void setTempoRestante(int tempoRestante) {
		this.tempoRestante = tempoRestante;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

}
