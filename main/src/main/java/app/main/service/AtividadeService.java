package app.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.main.entity.Atividade;
import app.main.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AtividadeService {
    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade save(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public List<Atividade> findAllOrderedByExpiration() {
        return atividadeRepository.findAllByOrderByDataExpiracaoAsc();
    }

    public void concluirAtividade(Long id) {
        Atividade atividade = atividadeRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada."));
        atividade.setSituacao(Atividade.Situacao.CONCLUIDA);
        atividadeRepository.save(atividade);
    }
}

