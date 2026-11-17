package FlowBank.FlowBank.Score.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Historico.DTO.HistoricoResponse;
import FlowBank.FlowBank.Historico.Model.Historico;
import FlowBank.FlowBank.Historico.Repository.HistoricoRepository;
import FlowBank.FlowBank.Score.DTO.ScoreResponse;
import FlowBank.FlowBank.Score.Model.ClassificacaoDeRisco;
import FlowBank.FlowBank.Score.Model.Score;
import FlowBank.FlowBank.Score.Repository.ScoreRepository;
import FlowBank.FlowBank.Transacao.DTO.TransacaoReferenciaResponse;
import FlowBank.FlowBank.Transacao.Model.Transacao;

@Service 
public class ScoreService {
    private  final ScoreRepository scoreRepository ;
    private  final HistoricoRepository historicoRepository;
   
    public ScoreService(ScoreRepository scoreRepository, HistoricoRepository historicoRepository) {
        this.scoreRepository = scoreRepository;
        this.historicoRepository = historicoRepository;
    }

    public ScoreResponse salvarScore(Long historicoid){
        Historico historico = buscarHistorico(historicoid);
        Integer scorefinal = CalcularScoreFinal(historico);
        ClassificacaoDeRisco risco = verificarScoreRisco(scorefinal);
        Score score = new Score();
        score.setHistorico(historico);
        score.setScoreFinal(scorefinal);
        score.setRisco(risco);
        score.setDataAnalise(LocalDateTime.now());
       Score scoreSalvo = scoreRepository.save(score);
       return  Response(scoreSalvo);

    }

    private BigDecimal  calcularValorGasto(List<Transacao> transacao){
        return transacao.stream().map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private Integer ValidarLimiteAtual(Historico historico){
        Cliente cliente = historico.getCliente();
        if(cliente.getLimiteAtual().compareTo(new BigDecimal(1000.00))>=0){
            return 20;
        }
        return  30;
    }
    private Integer validarValorGasto(List<Transacao> transacao){
        BigDecimal valorGasto = calcularValorGasto(transacao);
        if(valorGasto.compareTo(new BigDecimal(5000.00))>=0){
            return 20;
        }
        return 30;
    }
    private Integer validarTempoDeHistorico(Historico historico){
        LocalDateTime dataAtual = LocalDateTime.now();

        long tempoHistorico = ChronoUnit.YEARS.between(
            historico.getDataRegistro(),
            dataAtual
        );
        if(tempoHistorico < 1){
            return 20;

        }
        return 30;
    }
    private Integer CalcularScoreFinal(Historico historico){
        Integer tempoHistorico = validarTempoDeHistorico(historico);
        Integer valor = validarValorGasto(historico.getCliente().getTransacao());
        Integer limite = ValidarLimiteAtual(historico);
        Integer scorefinal = tempoHistorico + valor + limite;
        return scorefinal;
    }
    private ClassificacaoDeRisco verificarScoreRisco(Integer scoreFinal){
        if(scoreFinal >= 70){
            return ClassificacaoDeRisco.ALTO;
        }
        else if(scoreFinal <= 50){
            return ClassificacaoDeRisco.MEDIO;
        }
        return ClassificacaoDeRisco.BAIXO;
    }
    private Historico buscarHistorico(long id){
        Historico historico = historicoRepository.findById(id).orElseThrow(()-> new RuntimeException("historico não encontrado"));
        return  historico;
    }
    private  ScoreResponse Response(Score score){
        ClienteReferenciaResponse clienteReferencia = new ClienteReferenciaResponse(score.getHistorico().getCliente().getId(), score.getHistorico().getCliente().getNome(), score.getHistorico().getCliente().getEmail());
        TransacaoReferenciaResponse referenciaResponse = new TransacaoReferenciaResponse(score.getHistorico().getTransacao().getId(),score.getHistorico().getTransacao().getValor(), score.getHistorico().getTransacao().getCategoria(),score.getHistorico().getTransacao().getDataDeGeracao());
        HistoricoResponse historicoResponse = new HistoricoResponse(score.getHistorico().getId(),score.getHistorico().getDataRegistro(), clienteReferencia,referenciaResponse);
        ScoreResponse scoreResponse = new ScoreResponse(score.getId(),score.getScoreFinal(),score.getDataAnalise(),score.getRisco(),historicoResponse);
        return scoreResponse;
    }
    
}
