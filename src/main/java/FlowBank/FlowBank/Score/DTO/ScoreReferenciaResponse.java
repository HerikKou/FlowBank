package FlowBank.FlowBank.Score.DTO;

import FlowBank.FlowBank.Score.Model.ClassificacaoDeRisco;

public record ScoreReferenciaResponse(
    Long id,
    Integer scoreFinal,
    ClassificacaoDeRisco risco
) {}
