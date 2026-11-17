package FlowBank.FlowBank.Funcionario.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Config.JwtTokenProvider;
import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;
import FlowBank.FlowBank.Funcionario.DTO.LoginResponse;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;

@Service 
public class LoginFuncionarioService {
    private final TransformarEmModel entidade;
    private final TransformarResponse resposta;
    private final JwtTokenProvider tokenProvider;
    
    
    public LoginFuncionarioService(TransformarEmModel entidade, TransformarResponse resposta,
            JwtTokenProvider tokenProvider) {
        this.entidade = entidade;
        this.resposta = resposta;
        this.tokenProvider = tokenProvider;
    }


    public LoginResponse loginFuncionario(LoginRequest request){
        Funcionario funcionario = entidade.loginFuncionario(request);
        String token = tokenProvider.gerarToken(funcionario.getEmail(), funcionario.getTipo().toString(), funcionario.getId());
        LoginResponse response = resposta.loginfuncionario(funcionario, token);
        return  response;
    }
}
