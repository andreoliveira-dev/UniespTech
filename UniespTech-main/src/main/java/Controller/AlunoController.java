package Controller;

import Service.AlunoService;
import Model.Aluno;

import java.util.Scanner;

public class  AlunoController {

    private AlunoService service = new AlunoService();

    public void iniciar() {

        Scanner leitor = new Scanner(System.in);

        while (true) {

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Sair");

            String opcao = leitor.nextLine();

            if (opcao.equals("1")) {

                System.out.println("Nome:");
                String nome = leitor.nextLine();

                System.out.println("CPF:");
                String cpf = leitor.nextLine();

                boolean sucesso = service.cadastrarAluno(nome, cpf);

                if (sucesso) {
                    System.out.println("Aluno cadastrado!");
                }
            }

            else if (opcao.equals("2")) {

                for (Aluno a : service.listarAlunos()) {
                    System.out.println(a.getNome() + " - " + a.getCpf());
                }

            }

            else if (opcao.equals("3")) {

                service.deletarTodos();
                System.out.println("Todos os alunos foram deletados");

            }

            else if (opcao.equals("4")) {
                break;
            }
        }

        leitor.close();
    }
}