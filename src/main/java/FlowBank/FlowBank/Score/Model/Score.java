package FlowBank.FlowBank.Score.Model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import FlowBank.FlowBank.Aprovacao.Model.Aprovacao;
import FlowBank.FlowBank.Historico.Model.Historico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O histórico é obrigatório")
    @ManyToOne
    @JoinColumn(name = "historico_id", nullable = false)
    private Historico historico;

    @OneToMany(mappedBy = "score")
    private List<Aprovacao> aprovacoes = new ArrayList<>();

    private Integer scoreFinal;

    @NotNull(message = "A data da análise é obrigatória")
    private LocalDateTime dataAnalise = LocalDateTime.now();

    @NotNull(message = "A classificação de risco é obrigatória")
    @Enumerated(EnumType.STRING)
    private ClassificacaoDeRisco risco;

    public Score() {
    }

    public Score(Long id, Historico historico, Integer scoreFinal, LocalDateTime dataAnalise, ClassificacaoDeRisco risco) {
        this.id = id;
        this.historico = historico;
        this.scoreFinal = scoreFinal;
        this.dataAnalise = dataAnalise;
        this.risco = risco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<Aprovacao> getAprovacoes() {
        return aprovacoes;
    }

    public void setAprovacoes(List<Aprovacao> aprovacoes) {
        this.aprovacoes = aprovacoes;
    }

    public Integer getScoreFinal() {
        return scoreFinal;
    }

    public void setScoreFinal(Integer scoreFinal) {
        this.scoreFinal = scoreFinal;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public ClassificacaoDeRisco getRisco() {
        return risco;
    }

    public void setRisco(ClassificacaoDeRisco risco) {
        this.risco = risco;
    }
    

}
    


