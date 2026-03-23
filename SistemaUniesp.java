import java.util.ArrayList;
import java.util.Scanner;


public class SistemaUniesp {
    

    static ArrayList<String> alunos_nomes = new ArrayList<>();
    static ArrayList<String> alunos_cpfs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        while (true) {
            System.out.println("======= SISTEMA ACADÊMICO UNIESP TECH =======");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Deletar Tudo (CUIDADO!)");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            
            String opcao = leitor.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Nome do Aluno: ");
                String nome = leitor.nextLine();
                System.out.print("CPF (Somente números): ");
                String cpf = leitor.nextLine();


                if (nome.isEmpty()) {
                    System.out.println("ERRO: Nome não pode ser vazio!");
                } else if (cpf.length() != 11) {
                    System.out.println("ERRO: CPF Inválido! Deve ter 11 dígitos.");
                } else {
                    alunos_nomes.add(nome);
                    alunos_cpfs.add(cpf);
                    System.out.println("Aluno cadastrado com sucesso!");
                }

            } else if (opcao.equals("2")) {
                System.out.println("--- LISTA DE ALUNOS ---");

                for (int i = 0; i < alunos_nomes.size(); i++) {
                    System.out.println("ID: " + i + " | Nome: " + alunos_nomes.get(i) + " | CPF: " + alunos_cpfs.get(i));
                }
                
            } else if (opcao.equals("3")) {
                // Bug clássico: Sem confirmação de segurança
                alunos_nomes.clear();
                alunos_cpfs.clear();
                System.out.println("Todos os dados foram apagados!");

            } else if (opcao.equals("4")) {
                System.out.println("Encerrando sistema...");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
            
            System.out.println("\n");
        }
        leitor.close();
    }
}
