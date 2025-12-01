package model;

/**
 * Classe que representa um Aluno no sistema de gestão educacional.
 * Estende a classe Usuario e implementa comportamentos específicos de aluno.
 */
public class Aluno extends Usuario implements Relatorio {
    private String matricula;
    private String curso;

    /**
     * Construtor da classe Aluno.
     * 
     * @param nome Nome do aluno
     * @param matricula Número de matrícula do aluno (será usado como login)
     * @param curso Curso ao qual o aluno está vinculado
     */
    public Aluno(String nome, String matricula, String curso) {
        super(nome, "", matricula, matricula); // email vazio, login = matrícula, senha = matrícula inicialmente
        this.matricula = matricula;
        this.curso = curso;
    }

    @Override
    public String getTipoUsuario() {
        return "Aluno";
    }
    
    // Getters e Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(getCabecalho());
        relatorio.append(String.format("Tipo: %s\n", getTipoUsuario()));
        relatorio.append(String.format("Nome: %s\n", getNome()));
        relatorio.append(String.format("Matrícula: %s\n", matricula));
        relatorio.append(String.format("Curso: %s\n", curso));
        relatorio.append(String.format("Email: %s\n", getEmail()));
        relatorio.append(String.format("Status de Autenticação: %s\n", 
                isAutenticado() ? "Autenticado" : "Não autenticado"));
        relatorio.append(getRodape());
        return relatorio.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s - Matrícula: %s - Curso: %s",
                super.toString(), matricula, curso);
    }
}
