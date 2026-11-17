package FlowBank.FlowBank.Transacao.DTO;

import java.math.BigDecimal;

import FlowBank.FlowBank.Transacao.Model.CategoriaGasto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RealizarTransacaoResponse(
     @NotNull
    Long id,
    
    @NotNull
    @DecimalMin("0.01")
    BigDecimal valor,

    @NotNull
    CategoriaGasto categoria,

    @NotNull
    Long clienteId
) {}
