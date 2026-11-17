package FlowBank.FlowBank.Solicitacao.DTO;

import java.math.BigDecimal;

public record SolicitacaoReferenciaResponse(
    Long id,
    BigDecimal limiteSolicitado,
    String motivo
) {}
