package FlowBank.FlowBank.Historico.DTO;

import java.time.LocalDateTime;

import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;
import FlowBank.FlowBank.Transacao.DTO.TransacaoReferenciaResponse;

public record HistoricoResponse(
    Long id,
    LocalDateTime dataRegistro,
    ClienteReferenciaResponse cliente,
    TransacaoReferenciaResponse transacao
) {}
