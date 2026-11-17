package FlowBank.FlowBank.Solicitacao.DTO;

import java.math.BigDecimal;

import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;

public record SolicitacaoResponse(
    Long id,
    BigDecimal limiteSolicitado,
    String motivo,
    ClienteReferenciaResponse cliente
) {}