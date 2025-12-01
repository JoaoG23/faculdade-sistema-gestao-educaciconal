package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa uma Turma no sistema de gestão educacional.
 * Uma turma relaciona um professor, um curso e uma lista de alunos.
 */
public class Turma {
    private String codigo;
    private Professor professor;
    private Curso curso;
    private List<Aluno> alunos;
    private Map<String, List<Avaliacao>> avaliacoes; // Mapa de matrícula para lista de avaliações

    /**
     * Construtor da classe Turma.
     * 
     * @param codigo Código identificador da turma
     * @param professor Professor responsável pela turma
     * @param curso Curso ao qual a turma pertence
     */
    public Turma(String codigo, Professor professor, Curso curso) {
        this.codigo = codigo;
        this.professor = professor;
        this.curso = curso;
        this.alunos = new ArrayList<>();
        this.avaliacoes = new HashMap<>();
    }

    /**
     * Adiciona um aluno à turma.
     * 
     * @param aluno Aluno a ser adicionado
     * @return true se o aluno foi adicionado com sucesso, false caso contrário
     */
    public boolean adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            return alunos.add(aluno);
        }
        return false;
    }

    /**
     * Remove um aluno da turma.
     * 
     * @param matricula Matrícula do aluno a ser removido
     * @return true se o aluno foi removido com sucesso, false caso contrário
     */
    public boolean removerAluno(String matricula) {
        return alunos.removeIf(aluno -> aluno.getMatricula().equals(matricula));
    }

    /**
     * Gera um resumo da turma contendo informações básicas.
     * 
     * @return String formatada com o resumo da turma
     */
    public String gerarResumo() {
        return String.format(
            "=== Resumo da Turma ===\n" +
            "Código: %s\n" +
            "Curso: %s (%s)\n" +
            "Professor: %s\n" +
            "Total de Alunos: %d",
            codigo,
            curso.getNome(),
            curso.getCodigo(),
            professor.getNome(),
            alunos.size()
        );
    }

    /**
     * Lista todos os alunos matriculados na turma.
     * 
     * @return String formatada com a lista de alunos
     */
    public String listarAlunos() {
        if (alunos.isEmpty()) {
            return "Nenhum aluno matriculado nesta turma.";
        }

        StringBuilder sb = new StringBuilder("=== Alunos Matriculados ===\n");
        for (Aluno aluno : alunos) {
            sb.append(String.format("- %s (Matrícula: %s)\n", 
                aluno.getNome(), 
                aluno.getMatricula()));
        }
        return sb.toString();
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Adiciona uma nova avaliação para um aluno da turma.
     * 
     * @param matricula Matrícula do aluno
     * @param avaliacao Avaliação a ser adicionada
     * @return true se a avaliação foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionarAvaliacao(String matricula, Avaliacao avaliacao) {
        if (!alunos.stream().anyMatch(a -> a.getMatricula().equals(matricula))) {
            return false; // Aluno não pertence à turma
        }
        
        avaliacoes.putIfAbsent(matricula, new ArrayList<>());
        return avaliacoes.get(matricula).add(avaliacao);
    }
    
    /**
     * Obtém todas as avaliações de um aluno específico.
     * 
     * @param matricula Matrícula do aluno
     * @return Lista de avaliações do aluno ou lista vazia se não houver avaliações
     */
    public List<Avaliacao> getAvaliacoesAluno(String matricula) {
        return new ArrayList<>(avaliacoes.getOrDefault(matricula, new ArrayList<>()));
    }
    
    /**
     * Calcula a média das notas de um aluno na turma.
     * 
     * @param matricula Matrícula do aluno
     * @return Média das notas ou -1 se o aluno não tiver avaliações
     */
    public double calcularMediaAluno(String matricula) {
        List<Avaliacao> avaliacoesAluno = avaliacoes.getOrDefault(matricula, new ArrayList<>());
        if (avaliacoesAluno.isEmpty()) {
            return -1;
        }
        
        double soma = 0;
        int contador = 0;
        
        for (Avaliacao aval : avaliacoesAluno) {
            if (aval.getNota() >= 0) { // Considera apenas avaliações com nota atribuída
                soma += aval.getNota();
                contador++;
            }
        }
        
        return contador > 0 ? Math.round((soma / contador) * 100.0) / 100.0 : -1;
    }
    
    /**
     * Gera um relatório de desempenho da turma.
     * 
     * @return String formatada com o relatório
     */
    public String gerarRelatorioDesempenho() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Relatório de Desempenho - %s ===\n", codigo));
        
        for (Aluno aluno : alunos) {
            String matricula = aluno.getMatricula();
            double media = calcularMediaAluno(matricula);
            String situacao = (media >= 6.0) ? "Aprovado" : (media == -1 ? "Sem avaliações" : "Em recuperação");
            
            sb.append(String.format("\nAluno: %s (Matrícula: %s)\n", aluno.getNome(), matricula));
            
            List<Avaliacao> avals = getAvaliacoesAluno(matricula);
            if (avals.isEmpty()) {
                sb.append("  Nenhuma avaliação registrada\n");
            } else {
                for (Avaliacao aval : avals) {
                    sb.append(String.format("  - %s: %s\n", 
                        aval.getDescricao(),
                        aval.getNota() == -1 ? "Não avaliado" : String.format("%.2f", aval.getNota())));
                }
                sb.append(String.format("  Média: %s - %s\n", 
                    media == -1 ? "N/A" : String.format("%.2f", media),
                    situacao));
            }
        }
        
        return sb.toString();
    }
    
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos); // Retorna uma cópia para evitar modificações externas
    }

    @Override
    public String toString() {
        return String.format("Turma %s - %s - Professor: %s - Alunos: %d", 
            codigo, 
            curso.getNome(), 
            professor.getNome(), 
            alunos.size());
    }
}
