package FlowBank.FlowBank.Funcionario.DTO;

import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;

public record FuncionarioReferenciaResponse(
    Long id,
    String nome,
    TipoFuncionario tipo,
    String email
) {}