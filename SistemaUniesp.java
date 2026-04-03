import controller.AlunoController;
import model.Aluno;
import repository.AlunoRepositoryMemoria;
import service.AlunoService;

import java.util.List;
import java.util.Scanner;

public class SistemaUniesp {

    public static void main(String[] args) {

        AlunoRepositoryMemoria repository = new AlunoRepositoryMemoria();
        AlunoService           service    = new AlunoService(repository);
        AlunoController        controller = new AlunoController(service);

        Scanner leitor = new Scanner(System.in);

        while (true) {
            System.out.println("======= SISTEMA ACADÊMICO UNIESP TECH =======");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Deletar Tudo (CUIDADO!)");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            String opcao = leitor.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Nome do Aluno: ");
                    String nome = leitor.nextLine();
                    System.out.print("CPF (somente números): ");
                    String cpf = leitor.nextLine();
                    System.out.println(controller.cadastrarAluno(nome, cpf));
                }
                case "2" -> {
                    List<Aluno> alunos = controller.listarAlunos();
                    System.out.println("--- LISTA DE ALUNOS ---");
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        alunos.forEach(System.out::println);
                    }
                }
                case "3" -> {
                    System.out.print("Tem certeza? Isso apagará TODOS os dados. (s/n): ");
                    String confirmacao = leitor.nextLine();
                    System.out.println(controller.deletarTodos(confirmacao.equalsIgnoreCase("s")));
                }
                case "4" -> {
                    System.out.println("Encerrando sistema...");
                    leitor.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }

            System.out.println();
        }
    }
}
