package FlowBank.FlowBank.Cliente.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.LoginClienteResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Config.JwtTokenProvider;
import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;

@Service 
public class LoginService {
    private final TransformarEmResponse response;
    private  final TransformarModel entidade;
    private final ClienteRepository repository;
    private final JwtTokenProvider tokenProvider;
    

    public LoginService(TransformarEmResponse response, TransformarModel entidade, ClienteRepository repository,
            JwtTokenProvider tokenProvider) {
        this.response = response;
        this.entidade = entidade;
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }


    public LoginClienteResponse logar(LoginRequest request){

        Cliente cliente = entidade.TransformarModel(request);
        String token = tokenProvider.gerarToken(cliente.getEmail(), "CLIENTE" , cliente.getId());
        LoginClienteResponse resposta = response.Login(cliente, token);
        return  resposta;
    }
}
