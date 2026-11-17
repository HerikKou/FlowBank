package FlowBank.FlowBank.Funcionario.DTO;

import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;

public record LoginResponse(
    
    Long id,
    String nome,
    String email,
    TipoFuncionario tipoUsuario,
    String token
) {}
