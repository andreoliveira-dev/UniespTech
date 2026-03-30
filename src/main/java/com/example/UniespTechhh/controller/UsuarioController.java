package com.example.UniespTechhh.controller; //para n se assustar veja o readme primeiro ksksk coloquei uma mensagem bem legal quando esse carrosel roda

import com.example.UniespTechhh.model.Usuario;
import com.example.UniespTechhh.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Avisa pro Spring que aqui é a central de rotas da internet pq ne se ele num sabe oq e oq como vai direcionar o cadastro e os outros? ele n e a Raven n
@RequestMapping("/usuarios") // Tudo que começar com /usuarios vem parar aqui
public class UsuarioController {

    @Autowired
    private UsuarioService servico; // Chama o cara que faz o servico pesado "trabaia escravo trabaia"

//Atenção explicação pq usei id em deletar e atualizar, só pra ter mais controle e n mudar o nome do banco todo para barão da pisadinha

    //Agora vc pode spikar com o POST no http://localhost:8080/usuarios/cadastrar
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @RequestBody Usuario user) {
        //O @Valid ali em cima e o ajudante do anti b.o se o CPF tiver errado ele num deixa passa
        servico.salvar(user);
        // Ajustado para getPerfil() pra n dar erro de compilação já que mudamos no Model!
        return "Usuário " + user.getNome() + " salvo com sucesso! (Nível: " + user.getPerfil() + ")";
    }


    //Manda spikar o GET no  http://localhost:8080/usuarios/listar e ve o "do ba ridu" rolar e aquele meme dos caras cantando pra o banco de dados voltar
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return servico.listarTodos(); // Pega a lista de geral do banco e como diria smzinho "Agora sou eu e vc"
    }


    //Manda um PUT no http://localhost:8080/usuarios/atualizar/{id} (EU DE DPS NA MADRUGADA COLOCA SÓ O NUMEROOO N AS{}) precisa disso aqui pra ele falar apague só fulanoooo tive que dar uma mexida pq fui ajeitar o deletar aí fui catucando tudo
    @PutMapping("/atualizar/{id}")
    public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario user) {
        // Coloquei o @Valid aqui também porque ninguém quer atualizar com CPF de 3 números né? kkkk
        return servico.atualizar(id, user);
    }

    //Serviço mais ou menos agora e agente 007 inves de thanos
    //Manda um DELETE no http://localhost:8080/usuarios/deletar/{id} (EU DE DPS NA MADRUGADA COLOCA SÓ O NUMEROOO N AS{})
    @DeleteMapping("deletar/{id}")
    public String deletar(@PathVariable Long id) {
        servico.deletarPorId(id);
        return "O meliante de ID " + id + " foi removido com sucesso!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        // Aqui a gente avisa que o Barão da Pisadinha tá online
        return "{\"status\": \"UP\", \"message\": \"Sistema UniespTech operacional e o H2 tá vivinho!\"}";
    }
}