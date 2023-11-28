package sistemasOperacionais;

public class Processo {

    private static int contadorIDs = 0;
    private int id;
    private int tempoExecucao;
    private int tempoChegada;
    private int prioridade;

    public Processo(int tempoExecucao, int tempoChegada, int prioridade) {
        this.id = ++contadorIDs;
        this.tempoExecucao = tempoExecucao;
        this.tempoChegada = tempoChegada;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "ID = " + id +
                ", Tempo Execucao = " + tempoExecucao +
                ", Tempo Chegada = " + tempoChegada +
                ", Prioridade = " + prioridade +
                '}';
    }
}
