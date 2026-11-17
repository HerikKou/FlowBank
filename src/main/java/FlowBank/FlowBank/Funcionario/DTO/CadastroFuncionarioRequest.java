package FlowBank.FlowBank.Funcionario.DTO;

import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroFuncionarioRequest(
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
    String senha,

    @NotNull
    TipoFuncionario tipo
) {}