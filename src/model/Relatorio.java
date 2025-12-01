package model;

/**
 * Interface que define o contrato para geração de relatórios.
 * Permite que diferentes entidades do sistema gerem relatórios de forma padronizada.
 */
public interface Relatorio {
    
    /**
     * Gera um relatório detalhado da entidade.
     * 
     * @return String contendo o relatório formatado
     */
    String gerarRelatorio();
    
    /**
     * Retorna o cabeçalho do relatório.
     * 
     * @return String com o cabeçalho formatado
     */
    default String getCabecalho() {
        return "=== RELATÓRIO ===\n";
    }
    
    /**
     * Retorna o rodapé do relatório.
     * 
     * @return String com o rodapé formatado
     */
    default String getRodape() {
        return "\n===================\n";
    }
}
