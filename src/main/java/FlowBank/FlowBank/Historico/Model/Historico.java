package FlowBank.FlowBank.Historico.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Score.Model.Score;
import FlowBank.FlowBank.Transacao.Model.Transacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Historico {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente é obrigatório")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "transacao_id")
    @NotNull(message = "A transacao é obrigatória")
    private Transacao transacao;
    @NotNull(message = "A data do histórico é obrigatória")
    private LocalDateTime dataRegistro = LocalDateTime.now();
    @OneToMany(mappedBy = "historico")
private List<Score> scores = new ArrayList<>();
    public Historico() {
    }
    public Historico(Long id,  Cliente cliente, Transacao transacao, LocalDateTime dataRegistro) {
        this.id = id;
        this.cliente = cliente;
        this.transacao = transacao;
        this.dataRegistro = dataRegistro;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Transacao getTransacao() {
        return transacao;
    }
    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }
    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    public List<Score> getScores() {
        return scores;
    }
    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
    
    
}
