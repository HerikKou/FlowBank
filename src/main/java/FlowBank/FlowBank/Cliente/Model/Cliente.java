package FlowBank.FlowBank.Cliente.Model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import FlowBank.FlowBank.Historico.Model.Historico;
import  FlowBank.FlowBank.Pessoa.Model.*;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;
import FlowBank.FlowBank.Transacao.Model.*;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
@Entity 
public class Cliente  extends Pessoa{
    @NotNull(message = "O limite atual é obrigatório")
    private BigDecimal limiteAtual;
    @OneToMany(mappedBy = "cliente")
    private List<Transacao> transacao = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacao = new ArrayList<>();
     @OneToMany(mappedBy = "cliente")
    private List<Historico> historico = new ArrayList<>();
    public Cliente(Long id, String nome, String email, String cpf,
               String senha, BigDecimal limiteAtual) {
    super(id, nome, email, cpf, senha);
    this.limiteAtual = limiteAtual;
}
public Cliente(){}
    public BigDecimal getLimiteAtual() {
        return limiteAtual;
    }
    public void setLimiteAtual(BigDecimal limiteAtual) {
        this.limiteAtual = limiteAtual;
    }
    public List<Transacao> getTransacao() {
        return transacao;
    }
    public void setTransacao(List<Transacao> transacao) {
        this.transacao = transacao;
    }
    public List<Solicitacao> getSolicitacao() {
        return solicitacao;
    }
    public void setSolicitacao(List<Solicitacao> solicitacao) {
        this.solicitacao = solicitacao;
    }
    public List<Historico> getHistorico() {
        return historico;
    }
    public void setHistorico(List<Historico> historico) {
        this.historico = historico;
    }

     

    
}
