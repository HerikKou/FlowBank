package FlowBank.FlowBank.Funcionario.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteResponse;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioResponse;
import FlowBank.FlowBank.Funcionario.DTO.LoginResponse;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;

@Service 
public class TransformarResponse {
    
    public CadastroFuncionarioResponse cadastrarFuncionario(Funcionario funcionario){
        return  new CadastroFuncionarioResponse(funcionario.getId(),funcionario.getNome(),funcionario.getEmail(),funcionario.getTipo());
    }
    public LoginResponse loginfuncionario(Funcionario funcionario,String token){
        return new 
        LoginResponse(funcionario.getId(), 
        funcionario.getNome(), 
        funcionario.getEmail(),
         funcionario.getTipo(), token);
    }
}
