package FlowBank.FlowBank.Cliente.DTO;

import java.math.BigDecimal;

public record CadastroClienteResponse(
    Long id,
    String nome,
    String email,
    BigDecimal limiteAtual
) {}