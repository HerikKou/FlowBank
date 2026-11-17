package FlowBank.FlowBank.Cliente.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteRequest;
import FlowBank.FlowBank.Cliente.DTO.CadastroClienteResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;

@Service 
public class CadastroService {
    
   
    private  final TransformarEmResponse response;
    private final TransformarModel model;
    public CadastroService(TransformarEmResponse response, TransformarModel model) {
        
        this.response = response;
        this.model = model;
    }
    
    public CadastroClienteResponse CadastrarCliente(CadastroClienteRequest request){
        Cliente cliente = model.TransformarModel(request);
        CadastroClienteResponse resposta = response.TransformarResponse(cliente);
        return resposta;
    }
}
