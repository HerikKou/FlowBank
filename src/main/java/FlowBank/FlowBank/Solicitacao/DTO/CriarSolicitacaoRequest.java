package FlowBank.FlowBank.Solicitacao.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarSolicitacaoRequest(
    @NotNull
    @DecimalMin("0.01")
    BigDecimal limiteSolicitado,

    @NotBlank
    String motivo

    
) {}