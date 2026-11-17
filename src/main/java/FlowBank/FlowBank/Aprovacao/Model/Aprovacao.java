package FlowBank.FlowBank.Aprovacao.Model;

import java.time.LocalDateTime;

import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Score.Model.Score;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Aprovacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O Status da aprovação é obrigatorio")
    @Enumerated(EnumType.STRING)
    private StatusAprovacao status;
   
    private LocalDateTime dataAprovacao =  LocalDateTime.now();
    private String justificativa;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @NotNull(message = "O funcionario deve ser obrigatorio")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    @NotNull(message = "A Solicitacao deve ser obrigatoria")
    private Solicitacao solicitacao;
    @ManyToOne
@JoinColumn(name = "score_id")
private Score score;
    public Aprovacao() {
    }
    public Aprovacao(Long id, StatusAprovacao status, LocalDateTime dataAprovacao, String justificativa,
            Funcionario funcionario, Solicitacao solicitacao,Score score) {
        this.id = id;
        this.status = status;
        this.dataAprovacao = dataAprovacao;
        this.justificativa = justificativa;
        this.funcionario = funcionario;
        this.solicitacao = solicitacao;
        this.score = score;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public StatusAprovacao getStatus() {
        return status;
    }
    public void setStatus(StatusAprovacao status) {
        this.status = status;
    }
    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }
    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    public String getJustificativa() {
        return justificativa;
    }
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
    public Score getScore() {
        return score;
    }
    public void setScore(Score score) {
        this.score = score;
    }
    
    
}
