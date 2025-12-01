package model;

/**
 * Classe que representa um Professor no sistema de gestão educacional.
 * Estende a classe Usuario e implementa comportamentos específicos de professor.
 */
public class Professor extends Usuario implements Relatorio {
    private String especialidade;
    private String registro;

    /**
     * Construtor da classe Professor.
     * 
     * @param nome Nome do professor
     * @param especialidade Área de especialização do professor
     * @param registro Número de registro profissional (será usado como login)
     */
    public Professor(String nome, String especialidade, String registro) {
        super(nome, nome.toLowerCase().replace(" ", ".") + "@escola.edu.br", 
              registro, registro); // senha = registro inicialmente
        this.especialidade = especialidade;
        this.registro = registro;
    }

    @Override
    public String getTipoUsuario() {
        return "Professor";
    }
    
    // Getters e Setters

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(getCabecalho());
        relatorio.append(String.format("Tipo: %s\n", getTipoUsuario()));
        relatorio.append(String.format("Nome: %s\n", getNome()));
        relatorio.append(String.format("Registro: %s\n", registro));
        relatorio.append(String.format("Especialidade: %s\n", especialidade));
        relatorio.append(String.format("Email: %s\n", getEmail()));
        relatorio.append(String.format("Status de Autenticação: %s\n", 
                isAutenticado() ? "Autenticado" : "Não autenticado"));
        relatorio.append(getRodape());
        return relatorio.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s - Especialidade: %s - Registro: %s", 
                super.toString(), especialidade, registro);
    }
}
