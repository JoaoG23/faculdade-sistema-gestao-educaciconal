import model.Aluno;
import model.Professor;
import model.Curso;
import model.CursoPresencial;
import model.CursoEAD;
import model.Turma;
import model.Avaliacao;
import model.Administrador;
import model.Relatorio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

/**
 * Classe principal que demonstra o uso das classes do sistema de gestão
 * educacional.
 */
public class Main {

    // Listas para armazenar os dados
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestão Educacional ===\n");

        // Dados iniciais de exemplo - MOVIDO PARA DENTRO DO MÉTODO MAIN
        inicializarDadosExemplo();

        // Menu principal
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Professores");
            System.out.println("3. Gerenciar Cursos");
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        menuAlunos();
                        break;
                    case 2:
                        menuProfessores();
                        break;
                    case 3:
                        menuCursos();
                        break;
                    case 4:
                        menuRelatorios();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido!");
                scanner.nextLine(); // Limpar o buffer
                opcao = -1;
            }

        } while (opcao != 0);

        scanner.close();
    }

    /**
     * Método para inicializar dados de exemplo do sistema
     */
    private static void inicializarDadosExemplo() {
        // Dados iniciais de exemplo
        CursoPresencial cursoTI = new CursoPresencial(
                "Tecnologia em Análise e Desenvolvimento de Sistemas",
                "TADS-2023",
                2400,
                "Sala 305 - Bloco B");
        cursos.add(cursoTI);

        CursoEAD cursoAdm = new CursoEAD(
                "Administração",
                "ADM-2023-EAD",
                2000,
                "Plataforma Unicesumar",
                "https://ead.unicesumar.edu.br");
        cursos.add(cursoAdm);

        // Adicionando alguns alunos de exemplo
        Aluno aluno1 = new Aluno("João Silva", "2023001", cursoTI.getNome());
        Aluno aluno2 = new Aluno("Maria Santos", "2023002", cursoAdm.getNome());
        alunos.add(aluno1);
        alunos.add(aluno2);

        // Adicionando alguns professores de exemplo
        Professor professor1 = new Professor("Carlos Oliveira", "Programação", "PROF-001");
        Professor professor2 = new Professor("Ana Paula", "Administração", "PROF-002");
        professores.add(professor1);
        professores.add(professor2);

        // Criando um administrador
        Administrador admin = new Administrador("Admin", "admin@escola.edu.br",
                "admin", "admin123", "Tecnologia da Informação");

        // Exemplo de uso das turmas
        Turma turmaTI = new Turma("TADS-2023-1", professor1, cursoTI);
        Turma turmaAdm = new Turma("ADM-2023-1", professor2, cursoAdm);

        // Matriculando alunos nas turmas de exemplo
        turmaTI.adicionarAluno(aluno1);
        turmaAdm.adicionarAluno(aluno2);

        System.out.println("Dados de exemplo carregados com sucesso!");
    }

    // Métodos para o menu de Alunos
    private static void menuAlunos() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE ALUNOS ===");
            System.out.println("1. Cadastrar novo aluno");
            System.out.println("2. Listar todos os alunos");
            System.out.println("3. Buscar aluno por matrícula");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        cadastrarAluno();
                        break;
                    case 2:
                        listarAlunos();
                        break;
                    case 3:
                        buscarAlunoPorMatricula();
                        break;
                    case 4:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido!");
                scanner.nextLine(); // Limpar o buffer
                opcao = -1;
            }

        } while (opcao != 4);
    }

    private static void cadastrarAluno() {
        System.out.println("\n=== CADASTRO DE ALUNO ===");
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.println("\nCursos disponíveis:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, cursos.get(i).getNome());
        }

        System.out.print("Selecione o número do curso: ");
        int cursoIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpar o buffer

        if (cursoIndex >= 0 && cursoIndex < cursos.size()) {
            String nomeCurso = cursos.get(cursoIndex).getNome();
            Aluno novoAluno = new Aluno(nome, matricula, nomeCurso);
            alunos.add(novoAluno);
            System.out.println("\nAluno cadastrado com sucesso!");
            System.out.println(novoAluno);
        } else {
            System.out.println("Índice de curso inválido!");
        }
    }

    private static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("\nNenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE ALUNOS ===");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    private static void buscarAlunoPorMatricula() {
        System.out.print("\nDigite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println("\nAluno encontrado:");
                System.out.println(aluno.gerarRelatorio());
                return;
            }
        }

        System.out.println("Aluno não encontrado com a matrícula: " + matricula);
    }

    // Métodos para o menu de Professores
    private static void menuProfessores() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE PROFESSORES ===");
            System.out.println("1. Cadastrar novo professor");
            System.out.println("2. Listar todos os professores");
            System.out.println("3. Buscar professor por registro");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        cadastrarProfessor();
                        break;
                    case 2:
                        listarProfessores();
                        break;
                    case 3:
                        buscarProfessorPorRegistro();
                        break;
                    case 4:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido!");
                scanner.nextLine(); // Limpar o buffer
                opcao = -1;
            }

        } while (opcao != 4);
    }

    private static void cadastrarProfessor() {
        System.out.println("\n=== CADASTRO DE PROFESSOR ===");
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();

        System.out.print("Área de especialização: ");
        String especialidade = scanner.nextLine();

        System.out.print("Número de registro: ");
        String registro = scanner.nextLine();

        Professor novoProfessor = new Professor(nome, especialidade, registro);
        professores.add(novoProfessor);
        System.out.println("\nProfessor cadastrado com sucesso!");
        System.out.println(novoProfessor);
    }

    private static void listarProfessores() {
        if (professores.isEmpty()) {
            System.out.println("\nNenhum professor cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE PROFESSORES ===");
        for (Professor professor : professores) {
            System.out.println(professor);
        }
    }

    private static void buscarProfessorPorRegistro() {
        System.out.print("\nDigite o número de registro do professor: ");
        String registro = scanner.nextLine();

        for (Professor professor : professores) {
            if (professor.getRegistro().equalsIgnoreCase(registro)) {
                System.out.println("\nProfessor encontrado:");
                System.out.println(professor.gerarRelatorio());
                return;
            }
        }

        System.out.println("Professor não encontrado com o registro: " + registro);
    }

    // Métodos para o menu de Cursos
    private static void menuCursos() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE CURSOS ===");
            System.out.println("1. Cadastrar novo curso presencial");
            System.out.println("2. Cadastrar novo curso EAD");
            System.out.println("3. Listar todos os cursos");
            System.out.println("4. Buscar curso por código");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        cadastrarCursoPresencial();
                        break;
                    case 2:
                        cadastrarCursoEAD();
                        break;
                    case 3:
                        listarCursos();
                        break;
                    case 4:
                        buscarCursoPorCodigo();
                        break;
                    case 5:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido!");
                scanner.nextLine(); // Limpar o buffer
                opcao = -1;
            }

        } while (opcao != 5);
    }

    private static void cadastrarCursoPresencial() {
        System.out.println("\n=== CADASTRO DE CURSO PRESENCIAL ===");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();

        System.out.print("Código do curso: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Sala de aula: ");
        String salaAula = scanner.nextLine();

        CursoPresencial novoCurso = new CursoPresencial(nome, codigo, cargaHoraria, salaAula);
        cursos.add(novoCurso);
        System.out.println("\nCurso presencial cadastrado com sucesso!");
        System.out.println(novoCurso);
    }

    private static void cadastrarCursoEAD() {
        System.out.println("\n=== CADASTRO DE CURSO EAD ===");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();

        System.out.print("Código do curso: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Plataforma virtual: ");
        String plataforma = scanner.nextLine();

        System.out.print("Link de acesso: ");
        String linkAcesso = scanner.nextLine();

        CursoEAD novoCurso = new CursoEAD(nome, codigo, cargaHoraria, plataforma, linkAcesso);
        cursos.add(novoCurso);
        System.out.println("\nCurso EAD cadastrado com sucesso!");
        System.out.println(novoCurso);
    }

    private static void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("\nNenhum curso cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE CURSOS ===");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, cursos.get(i));
        }
    }

    private static void buscarCursoPorCodigo() {
        System.out.print("\nDigite o código do curso: ");
        String codigo = scanner.nextLine();

        for (Curso curso : cursos) {
            if (curso.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("\nCurso encontrado:");
                System.out.println(curso.gerarRelatorio());
                return;
            }
        }

        System.out.println("Curso não encontrado com o código: " + codigo);
    }

    // Método para o menu de Relatórios
    private static void menuRelatorios() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE RELATÓRIOS ===");
            System.out.println("1. Relatório de todos os alunos");
            System.out.println("2. Relatório de todos os professores");
            System.out.println("3. Relatório de todos os cursos");
            System.out.println("4. Estatísticas dos cursos");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n=== RELATÓRIO DE ALUNOS ===");
                        for (Aluno aluno : alunos) {
                            System.out.println(aluno.gerarRelatorio());
                        }
                        break;
                    case 2:
                        System.out.println("\n=== RELATÓRIO DE PROFESSORES ===");
                        for (Professor professor : professores) {
                            System.out.println(professor.gerarRelatorio());
                        }
                        break;
                    case 3:
                        System.out.println("\n=== RELATÓRIO DE CURSOS ===");
                        for (Curso curso : cursos) {
                            System.out.println(curso.gerarRelatorio());
                        }
                        break;
                    case 4:
                        System.out.println("\n=== ESTATÍSTICAS DOS CURSOS ===");
                        for (Curso curso : cursos) {
                            System.out.println(curso.gerarEstatisticas());
                            System.out.println();
                        }
                        break;
                    case 5:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido!");
                scanner.nextLine(); // Limpar o buffer
                opcao = -1;
            }

        } while (opcao != 5);
    }
}