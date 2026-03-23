package SistemaUniesp.Felipe.util;

public class CpfValidator {
    public static boolean isCpfValido (String cpf) {
        if (cpf == null || cpf.length() != 11){
            return false;
        }
        int soma =0;
        for (int i = 0; i < 9; i++){
            soma =+ (cpf.charAt(i) - '0') *(10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 != (cpf.charAt(9) - '0')){
            return false;
        }
        soma = 0;

        for (int i = 0; i < 10; i++){
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) digito2 = 0;

        return digito2 == (cpf.charAt(10) - '0');
    }
}