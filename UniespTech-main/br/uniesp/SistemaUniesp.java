package br.uniesp;

import br.uniesp.controller.AlunoController;

public class SistemaUniesp {

    public static void main(String[] args) {
        AlunoController controller = new AlunoController();
        controller.iniciar();
    }
}