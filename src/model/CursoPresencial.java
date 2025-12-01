package model;

/**
 * Classe que representa um Curso Presencial, que herda de Curso.
 * Inclui informações específicas para cursos presenciais, como sala de aula.
 */
public class CursoPresencial extends Curso {
    private String salaAula;

    /**
     * Construtor da classe CursoPresencial.
     * 
     * @param nome Nome do curso
     * @param codigo Código identificador do curso
     * @param cargaHoraria Carga horária total em horas
     * @param salaAula Número/identificação da sala de aula
     */
    public CursoPresencial(String nome, String codigo, int cargaHoraria, String salaAula) {
        super(nome, codigo, cargaHoraria);
        this.salaAula = salaAula;
    }

    /**
     * Fornece detalhes específicos do curso presencial.
     * 
     * @return String com os detalhes do curso presencial
     */
    @Override
    public String detalharCurso() {
        return String.format("Modalidade: Presencial\nSala de Aula: %s\n" +
                           "Horário: A combinar com a coordenação\n" +
                           "Local: Prédio Acadêmico - Campus Central", 
                           salaAula);
    }

    /**
     * Fornece detalhes específicos do curso presencial para estatísticas.
     * 
     * @return String com os detalhes do curso presencial para estatísticas
     */
    @Override
    protected String getDetalhesEspecificosEstatisticas() {
        return String.format("""
            Modalidade: Presencial
            Sala de Aula: %s
            Taxa de Frequência Média: 85%% (exemplo)
            Horários de Aula: Segunda e Quarta, 19h-22h""", 
            salaAula);
    }

    @Override
    public String toString() {
        return "CursoPresencial{" +
                "salaAula='" + salaAula + '\'' +
                "} " + super.toString();
    }

    // Getters e Setters
    public String getSalaAula() {
        return salaAula;
    }

    public void setSalaAula(String salaAula) {
        this.salaAula = salaAula;
    }
}
