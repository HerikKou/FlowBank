package FlowBank.FlowBank.Funcionario.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.Service.TransformarModel;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioRequest;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioResponse;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;

@Service 
public class CadastrarFuncionarioService {
    private final TransformarEmModel entidade;
    private final TransformarResponse resposta;
    
    public CadastrarFuncionarioService(TransformarEmModel entidade, TransformarResponse resposta) {
        this.entidade = entidade;
        this.resposta = resposta;
    }

    public CadastroFuncionarioResponse cadastrarFuncionario(CadastroFuncionarioRequest request){
        Funcionario funcionario = entidade.cadastrofuncionario(request);
        CadastroFuncionarioResponse response = resposta.cadastrarFuncionario(funcionario);
        return  response;
    }
}
