package FlowBank.FlowBank.Score.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import FlowBank.FlowBank.Score.Model.Score;

public interface ScoreRepository extends JpaRepository<Score,Long> {
Optional<Score> findTopByHistorico_Cliente_IdOrderByDataAnaliseDesc(Long clienteId);
}
