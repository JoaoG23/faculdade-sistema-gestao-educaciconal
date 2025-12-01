package model;

/**
 * Classe abstrata que representa um Curso no sistema de gestão educacional.
 * Define a estrutura básica que todos os cursos devem ter.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um Curso no sistema de gestão educacional.
 * Define a estrutura básica que todos os cursos devem ter.
 */
public abstract class Curso implements Relatorio {
    private List<Aluno> alunosMatriculados = new ArrayList<>();
    private String nome;
    private String codigo;
    private int cargaHoraria; // em horas

    /**
     * Construtor da classe Curso.
     * 
     * @param nome Nome do curso
     * @param codigo Código identificador do curso
     * @param cargaHoraria Carga horária total em horas
     */
    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses
     * para fornecer detalhes específicos do tipo de curso.
     * 
     * @return String com os detalhes específicos do curso
     */
    public abstract String detalharCurso();
    
    /**
     * Adiciona um aluno à lista de matriculados no curso.
     * 
     * @param aluno Aluno a ser matriculado
     */
    public void matricularAluno(Aluno aluno) {
        if (aluno != null && !alunosMatriculados.contains(aluno)) {
            alunosMatriculados.add(aluno);
        }
    }
    
    /**
     * Remove um aluno da lista de matriculados no curso.
     * 
     * @param aluno Aluno a ser removido
     * @return true se o aluno foi removido, false caso contrário
     */
    public boolean removerAluno(Aluno aluno) {
        return alunosMatriculados.remove(aluno);
    }
    
    /**
     * Retorna a quantidade de alunos matriculados no curso.
     * 
     * @return Número de alunos matriculados
     */
    public int getQuantidadeAlunos() {
        return alunosMatriculados.size();
    }
    
    /**
     * Gera um relatório com estatísticas do curso.
     * 
     * @return String com o relatório de estatísticas
     */
    public String gerarEstatisticas() {
        return String.format("""
            === ESTATÍSTICAS DO CURSO ===
            Nome: %s
            Código: %s
            Carga Horária: %d horas
            Alunos Matriculados: %d
            %s""", 
            nome, codigo, cargaHoraria, alunosMatriculados.size(), 
            getDetalhesEspecificosEstatisticas());
    }
    
    /**
     * Método abstrato para que as subclasses possam adicionar
     * detalhes específicos nas estatísticas.
     * 
     * @return String com detalhes específicos das estatísticas
     */
    protected abstract String getDetalhesEspecificosEstatisticas();
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(getCabecalho());
        relatorio.append(String.format("Tipo: Curso\n"));
        relatorio.append(String.format("Nome: %s\n", nome));
        relatorio.append(String.format("Código: %s\n", codigo));
        relatorio.append(String.format("Carga Horária: %d horas\n", cargaHoraria));
        relatorio.append(String.format("Alunos Matriculados: %d\n", alunosMatriculados.size()));
        relatorio.append("\nDetalhes do Curso:\n");
        relatorio.append(detalharCurso());
        relatorio.append("\n");
        relatorio.append(getRodape());
        return relatorio.toString();
    }
    
    @Override
    public String toString() {
        return String.format("Curso: %s (Código: %s, Carga Horária: %d horas)\n%s",
            nome, codigo, cargaHoraria, detalharCurso());
    }
}
