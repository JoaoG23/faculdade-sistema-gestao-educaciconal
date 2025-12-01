package model;

/**
 * Interface que define o contrato para autenticação de usuários no sistema.
 */
public interface Autenticavel {
    /**
     * Autentica um usuário com base no login e senha fornecidos.
     * 
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return true se a autenticação for bem-sucedida, false caso contrário
     */
    boolean autenticar(String login, String senha);
    
    /**
     * Altera a senha do usuário.
     * 
     * @param novaSenha Nova senha a ser definida
     * @return true se a senha foi alterada com sucesso, false caso contrário
     */
    boolean alterarSenha(String novaSenha);
}
