package app.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.main.entity.Endereco;
import app.main.entity.Pessoa;
import app.main.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("*")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) {
    
        if (pessoa.getEnderecos() != null) {
            for (Endereco endereco : pessoa.getEnderecos()) {
                endereco.setPessoa(pessoa);
            }
        }

        Pessoa pessoaSalva = pessoaService.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }


    @GetMapping
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable Long id) {
        return pessoaService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pessoaService.delete(id);
    }
}

