package FlowBank.FlowBank.Score.DTO;

import java.time.LocalDateTime;
import java.util.List;

import FlowBank.FlowBank.Aprovacao.DTO.AprovacaoResponse;
import FlowBank.FlowBank.Historico.DTO.HistoricoResponse;
import FlowBank.FlowBank.Score.Model.ClassificacaoDeRisco;

public record ScoreResponse(
    Long id,
    Integer scoreFinal,
    LocalDateTime dataAnalise,
    ClassificacaoDeRisco risco,
    HistoricoResponse historico
  
) {}
