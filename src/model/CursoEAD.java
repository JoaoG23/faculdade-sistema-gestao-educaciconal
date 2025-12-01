package model;

/**
 * Classe que representa um Curso EAD (Ensino a Distância), que herda de Curso.
 * Inclui informações específicas para cursos na modalidade EAD, como plataforma virtual.
 */
public class CursoEAD extends Curso {
    private String plataformaVirtual;
    private String linkAcesso;

    /**
     * Construtor da classe CursoEAD.
     * 
     * @param nome Nome do curso
     * @param codigo Código identificador do curso
     * @param cargaHoraria Carga horária total em horas
     * @param plataformaVirtual Nome da plataforma virtual utilizada
     * @param linkAcesso Link de acesso à plataforma
     */
    public CursoEAD(String nome, String codigo, int cargaHoraria, String plataformaVirtual, String linkAcesso) {
        super(nome, codigo, cargaHoraria);
        this.plataformaVirtual = plataformaVirtual;
        this.linkAcesso = linkAcesso;
    }

    /**
     * Fornece detalhes específicos do curso EAD.
     * 
     * @return String com os detalhes do curso EAD
     */
    @Override
    public String detalharCurso() {
        return String.format("Modalidade: Educação a Distância (EAD)\n" +
                           "Plataforma: %s\n" +
                           "Acesso: %s\n" +
                           "Suporte: suporte.ead@universidade.edu.br",
                           plataformaVirtual, linkAcesso);
    }

    // Getters e Setters
    public String getPlataformaVirtual() {
        return plataformaVirtual;
    }

    public void setPlataformaVirtual(String plataformaVirtual) {
        this.plataformaVirtual = plataformaVirtual;
    }

    public String getLinkAcesso() {
        return linkAcesso;
    }

    public void setLinkAcesso(String linkAcesso) {
        this.linkAcesso = linkAcesso;
    }
    
    @Override
    protected String getDetalhesEspecificosEstatisticas() {
        return String.format("""
            Modalidade: Educação a Distância (EAD)
            Plataforma: %s
            Taxa de Conclusão: 78%% (exemplo)
            Acesso: %s
            Média de Acesso Semanal: 3.5 dias (exemplo)""", 
            plataformaVirtual, linkAcesso);
    }
    
    @Override
    public String toString() {
        return "CursoEAD{" +
                "plataformaVirtual='" + plataformaVirtual + '\'' +
                ", linkAcesso='" + linkAcesso + '\'' +
                "} " + super.toString();
    }
}
