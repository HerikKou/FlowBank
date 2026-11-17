package FlowBank.FlowBank.Solicitacao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

}