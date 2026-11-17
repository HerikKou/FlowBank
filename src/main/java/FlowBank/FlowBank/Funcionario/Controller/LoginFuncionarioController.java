package FlowBank.FlowBank.Funcionario.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;
import FlowBank.FlowBank.Funcionario.DTO.LoginResponse;
import FlowBank.FlowBank.Funcionario.Service.CadastrarFuncionarioService;
import FlowBank.FlowBank.Funcionario.Service.LoginFuncionarioService;

@RestController
@RequestMapping("/api")
public class LoginFuncionarioController {
    
    private final LoginFuncionarioService service;

    public LoginFuncionarioController(LoginFuncionarioService service) {
        this.service = service;
    }
    
    @PostMapping("/loginFuncionario")
    public ResponseEntity<LoginResponse> loginFuncionario(@RequestBody LoginRequest request){
        LoginResponse response = service.loginFuncionario(request);
        return ResponseEntity.status(200).body(response);
    }
}
