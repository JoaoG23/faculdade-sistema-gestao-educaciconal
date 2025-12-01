package model;

/**
 * Classe que representa uma Avaliação no sistema de gestão educacional.
 * Garante que as notas estejam sempre dentro do intervalo válido (0 a 10).
 */
public class Avaliacao {
    private double nota;
    private String descricao;
    private Aluno aluno;
    private String codigoTurma;

    /**
     * Construtor da classe Avaliacao.
     * 
     * @param descricao Descrição da avaliação (ex: "Prova 1", "Trabalho Final")
     * @param aluno Aluno relacionado à avaliação
     * @param codigoTurma Código da turma à qual a avaliação pertence
     */
    public Avaliacao(String descricao, Aluno aluno, String codigoTurma) {
        this.descricao = descricao;
        this.aluno = aluno;
        this.codigoTurma = codigoTurma;
        this.nota = -1; // Valor inválido inicial, indicando que ainda não foi atribuída nota
    }

    /**
     * Atribui uma nota à avaliação, garantindo que esteja no intervalo válido (0 a 10).
     * 
     * @param valor Nota a ser atribuída (deve estar entre 0 e 10)
     * @return true se a nota foi atribuída com sucesso, false caso contrário
     */
    public boolean atribuirNota(double valor) {
        if (valor >= 0 && valor <= 10) {
            this.nota = Math.round(valor * 100.0) / 100.0; // Arredonda para 2 casas decimais
            return true;
        }
        return false;
    }

    /**
     * Verifica se a avaliação está aprovada (nota >= 6.0).
     * 
     * @return true se aprovado, false caso contrário
     */
    public boolean estaAprovado() {
        return nota >= 6.0;
    }

    // Getters
    public double getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    @Override
    public String toString() {
        String status = (nota == -1) ? "Não avaliado" : String.format("%.2f", nota);
        String aprovacao = (nota == -1) ? "" : (estaAprovado() ? " (Aprovado)" : " (Reprovado)");
        return String.format("%s - %s: %s%s", 
                aluno.getNome(), 
                descricao, 
                status,
                aprovacao);
    }
}
