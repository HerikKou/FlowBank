package FlowBank.FlowBank.Transacao.DTO;
import FlowBank.FlowBank.Transacao.Model.CategoriaGasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public record TransacaoReferenciaResponse(
    Long id,
    BigDecimal valor,
    CategoriaGasto categoria,
    LocalDateTime dataDeGeracao
) {}