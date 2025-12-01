import model.Aluno;
import model.Professor;
import model.CursoPresencial;
import model.CursoEAD;
import model.Turma;
import model.Avaliacao;
import model.Administrador;
import model.Relatorio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que demonstra o uso das classes do sistema de gestão educacional.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestão Educacional ===\n");
        
        // Criando instâncias de Curso (presencial e EAD)
        CursoPresencial cursoTI = new CursoPresencial(
            "Tecnologia em Análise e Desenvolvimento de Sistemas", 
            "TADS-2023", 
            2400, 
            "Sala 305 - Bloco B");
            
        CursoEAD cursoAdm = new CursoEAD(
            "Administração", 
            "ADM-2023-EAD", 
            2000,
            "Plataforma Unicesumar",
            "https://ead.unicesumar.edu.br");
        
        // Criando instâncias de Aluno
        Aluno aluno1 = new Aluno("João Silva", "2023001", cursoTI.getNome());
        Aluno aluno2 = new Aluno("Maria Santos", "2023002", cursoAdm.getNome());
        
        // Criando instâncias de Professor
        Professor professor1 = new Professor("Carlos Oliveira", "Programação", "PROF-001");
        Professor professor2 = new Professor("Ana Paula", "Administração", "PROF-002");
        
        // Criando um administrador
        Administrador admin = new Administrador("Admin", "admin@escola.edu.br", 
                                             "admin", "admin123", "Tecnologia da Informação");
        
  
        
        // Demonstração da classe Turma
        System.out.println("\n=== Gerenciamento de Turmas ===");
        
        // Exibindo detalhes dos cursos
        System.out.println("\n=== Detalhes dos Cursos ===");
        System.out.println(cursoTI);
        System.out.println("\n" + cursoAdm);
        
        // Criando turmas
        Turma turmaTI = new Turma("TADS-2023-1", professor1, cursoTI);
        Turma turmaAdm = new Turma("ADM-2023-1", professor2, cursoAdm);
        
        // Matriculando alunos nas turmas
        turmaTI.adicionarAluno(aluno1);
        turmaAdm.adicionarAluno(aluno2);
        
        // Adicionando mais alguns alunos para demonstração
        Aluno aluno3 = new Aluno("Pedro Oliveira", "2023003", cursoTI.getNome());
        Aluno aluno4 = new Aluno("Ana Costa", "2023004", cursoAdm.getNome());
        
        turmaTI.adicionarAluno(aluno3);
        turmaAdm.adicionarAluno(aluno4);
        
        // Exibindo resumo das turmas
        System.out.println("\n" + turmaTI.gerarResumo());
        System.out.println("\n" + turmaAdm.gerarResumo());
        
        // Listando alunos de uma turma
        System.out.println("\n" + turmaTI.listarAlunos());
        
        // Demonstração de remoção de aluno
        turmaTI.removerAluno("2023003");
        System.out.println("\nApós remoção de um aluno:");
        System.out.println(turmaTI.listarAlunos());
        
        // Criando lista de relatórios para demonstração
        List<Relatorio> relatorios = new ArrayList<>();
        relatorios.add(aluno1);
        relatorios.add(professor1);
        relatorios.add(cursoTI);
        relatorios.add(cursoAdm);
        
        // Menu interativo de relatórios
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== MENU DE RELATÓRIOS E ESTATÍSTICAS ===");
            System.out.println("1. Gerar relatório de todos os itens");
            System.out.println("2. Gerar relatório de um item específico");
            System.out.println("3. Exibir estatísticas dos cursos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        System.out.println("\n=== RELATÓRIOS DETALHADOS ===");
                        for (Relatorio relatorio : relatorios) {
                            System.out.println(relatorio.gerarRelatorio());
                        }
                        break;
                        
                    case 2:
                        System.out.println("\nSelecione o item para gerar relatório:");
                        System.out.println("1. Aluno 1");
                        System.out.println("2. Professor 1");
                        System.out.println("3. Curso de TI");
                        System.out.println("4. Curso de ADM");
                        System.out.print("Opção: ");
                        int item = Integer.parseInt(scanner.nextLine());
                        
                        switch (item) {
                            case 1:
                                System.out.println(aluno1.gerarRelatorio());
                                break;
                            case 2:
                                System.out.println(professor1.gerarRelatorio());
                                break;
                            case 3:
                                System.out.println(cursoTI.gerarRelatorio());
                                break;
                            case 4:
                                System.out.println(cursoAdm.gerarRelatorio());
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                        break;
                        
                    case 3:
                        System.out.println("\n=== ESTATÍSTICAS DOS CURSOS ===");
                        System.out.println(cursoTI.gerarEstatisticas());
                        System.out.println("\n" + cursoAdm.gerarEstatisticas());
                        break;
                        
                    case 4:
                        System.out.println("Encerrando o sistema...");
                        break;
                        
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                opcao = 0;
            }
            
        } while (opcao != 4);
        
        scanner.close();
        
        // Demonstração do sistema de avaliações
        // System.out.println("\n=== Sistema de Avaliações ===");
        
        // // Criando avaliações para os alunos
        // Avaliacao aval1Aluno1 = new Avaliacao("Prova 1", aluno1, turmaTI.getCodigo());
        // Avaliacao aval2Aluno1 = new Avaliacao("Prova 2", aluno1, turmaTI.getCodigo());
        // Avaliacao aval1Aluno2 = new Avaliacao("Prova 1", aluno2, turmaAdm.getCodigo());
        
        // // Atribuindo notas (todas as notas são validadas para estarem entre 0 e 10)
        // aval1Aluno1.atribuirNota(8.5);
        // aval2Aluno1.atribuirNota(7.0);
        // aval1Aluno2.atribuirNota(9.0);
        
        // // Adicionando as avaliações às turmas
        // turmaTI.adicionarAvaliacao(aluno1.getMatricula(), aval1Aluno1);
        // turmaTI.adicionarAvaliacao(aluno1.getMatricula(), aval2Aluno1);
        // turmaAdm.adicionarAvaliacao(aluno2.getMatricula(), aval1Aluno2);
        
        // // Tentativa de atribuir nota inválida (será rejeitada)
        // Avaliacao avalInvalida = new Avaliacao("Trabalho", aluno1, turmaTI.getCodigo());
        // boolean sucesso = avalInvalida.atribuirNota(11.0); // Nota inválida (deve estar entre 0 e 10)
        // System.out.println("\nTentativa de atribuir nota 11.0: " + (sucesso ? "Sucesso" : "Falha (como esperado)"));
        
        // // Exibindo relatório de desempenho das turmas
        // System.out.println("\n" + turmaTI.gerarRelatorioDesempenho());
        // System.out.println("\n" + turmaAdm.gerarRelatorioDesempenho());
    }
}
