package model;

/**
 * Classe abstrata que representa um usuário genérico do sistema.
 * Define atributos e comportamentos comuns a todos os usuários.
 */
public abstract class Usuario implements Autenticavel {
    protected String nome;
    protected String email;
    protected String login;
    protected String senha; // Em um sistema real, isso deveria ser criptografado
    protected boolean autenticado;

    /**
     * Construtor da classe Usuario.
     * 
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param login Login único do usuário
     * @param senha Senha inicial do usuário
     */
    public Usuario(String nome, String email, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha; // Em um sistema real, a senha deve ser armazenada de forma segura (hash + salt)
        this.autenticado = false;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        if (this.login.equals(login) && this.senha.equals(senha)) {
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }

    @Override
    public boolean alterarSenha(String novaSenha) {
        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            return false;
        }
        this.senha = novaSenha;
        return true;
    }

    public void deslogar() {
        this.autenticado = false;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    protected String getSenha() {
        return senha; // Em um sistema real, não deveríamos ter um getter para a senha
    }

    /**
     * Método abstrato que retorna o tipo do usuário.
     * Deve ser implementado pelas classes filhas.
     * 
     * @return String representando o tipo do usuário
     */
    public abstract String getTipoUsuario();
    
    @Override
    public String toString() {
        return String.format("%s: %s (Email: %s)", 
                getTipoUsuario(), nome, email);
    }
}
