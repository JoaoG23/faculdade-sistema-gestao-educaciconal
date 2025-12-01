package model;

/**
 * Classe que representa um Administrador no sistema de gestão educacional.
 * Tem acesso a funcionalidades administrativas do sistema.
 */
public class Administrador extends Usuario {
    private String departamento;

    /**
     * Construtor da classe Administrador.
     * 
     * @param nome Nome do administrador
     * @param email Email do administrador
     * @param login Login único do administrador
     * @param senha Senha do administrador
     * @param departamento Departamento de atuação
     */
    public Administrador(String nome, String email, String login, String senha, String departamento) {
        super(nome, email, login, senha);
        this.departamento = departamento;
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    /**
     * Método específico para administradores resetarem senhas de outros usuários.
     * 
     * @param usuario Usuário que terá a senha alterada
     * @param novaSenha Nova senha a ser definida
     * @return true se a senha foi alterada com sucesso, false caso contrário
     */
    public boolean resetarSenha(Usuario usuario, String novaSenha) {
        if (usuario == null || !this.isAutenticado()) {
            return false;
        }
        return usuario.alterarSenha(novaSenha);
    }

    // Getters e Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return String.format("%s - Departamento: %s", 
                super.toString(), departamento);
    }
}
