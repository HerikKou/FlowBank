package FlowBank.FlowBank.Funcionario.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteRequest;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioRequest;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioResponse;
import FlowBank.FlowBank.Funcionario.Service.CadastrarFuncionarioService;

@RestController 
@RequestMapping("/api")
public class CadastroFuncionarioController {
    private  final CadastrarFuncionarioService service;
    
    public CadastroFuncionarioController(CadastrarFuncionarioService service) {
        this.service = service;
    }

    @PostMapping("/cadastroFuncionario")
    public ResponseEntity<CadastroFuncionarioResponse> cadastrar(@RequestBody CadastroFuncionarioRequest request){
        CadastroFuncionarioResponse response = service.cadastrarFuncionario(request);
        return ResponseEntity.status(201).body(response);
    }
}
