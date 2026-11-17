package FlowBank.FlowBank.Cliente.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Cliente.DTO.LoginClienteResponse;
import FlowBank.FlowBank.Cliente.Service.LoginService;
import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;

@RestController 
@RequestMapping("/api")
public class LoginCliente {
    private  final LoginService service;
    public LoginCliente(LoginService service){
        this.service = service;
    }
    @PostMapping("/loginCliente")
    public ResponseEntity<LoginClienteResponse> logar(@RequestBody LoginRequest request){
        LoginClienteResponse response = service.logar(request);
        return ResponseEntity.status(200).body(response);
    }
}
