package FlowBank.FlowBank.Funcionario.DTO;

import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;

public record CadastroFuncionarioResponse(
    Long id,
    String nome,
    String email,
    TipoFuncionario tipo
) {}
