package app.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.main.entity.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findAllByOrderByDataExpiracaoAsc();
}

