package FlowBank.FlowBank.Cliente.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteResponse;
import FlowBank.FlowBank.Cliente.DTO.LoginClienteResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Funcionario.DTO.LoginResponse;

@Service 
public class TransformarEmResponse {
    
    private final BigDecimal limiteAtual = new BigDecimal(100.00);
    
    public CadastroClienteResponse TransformarResponse(Cliente cliente){
        
        CadastroClienteResponse response = new 
        CadastroClienteResponse(cliente.getId()
        ,cliente.getNome(),cliente.getEmail(),limiteAtual);
        
        return response;
    }

    public LoginClienteResponse Login(Cliente cliente,String token){
        return  new LoginClienteResponse(cliente.getId(),cliente.getEmail(),cliente.getSenha(),token);
    }
}
