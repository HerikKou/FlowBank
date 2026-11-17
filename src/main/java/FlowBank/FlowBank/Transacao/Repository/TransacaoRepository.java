package FlowBank.FlowBank.Transacao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import FlowBank.FlowBank.Transacao.Model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao,Long>{

}
