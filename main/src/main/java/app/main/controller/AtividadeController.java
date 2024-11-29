package app.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.main.entity.Atividade;
import app.main.entity.Endereco;
import app.main.service.AtividadeService;

@RestController
@RequestMapping("/atividades")
@CrossOrigin("*")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public Atividade create(@RequestBody Atividade atividade) {
  
        if (atividade.getPessoa() != null && atividade.getPessoa().getEnderecos() != null) {
            for (Endereco endereco : atividade.getPessoa().getEnderecos()) {
                endereco.setPessoa(atividade.getPessoa()); 
            }
        }

        return atividadeService.save(atividade);
    }


    @GetMapping("/ordenadas")
    public List<Atividade> findAllOrderedByExpiration() {
        return atividadeService.findAllOrderedByExpiration();
    }

    @PutMapping("/concluir/{id}")
    public void concluir(@PathVariable Long id) {
        atividadeService.concluirAtividade(id);
    }
}

