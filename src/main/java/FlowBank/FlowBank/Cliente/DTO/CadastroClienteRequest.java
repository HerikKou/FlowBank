package FlowBank.FlowBank.Cliente.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroClienteRequest(
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    String cpf,

    @NotBlank
    @Size(min = 8)
    String senha

    
) {} 
