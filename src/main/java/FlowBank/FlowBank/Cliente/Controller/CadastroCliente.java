package FlowBank.FlowBank.Cliente.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteRequest;
import FlowBank.FlowBank.Cliente.DTO.CadastroClienteResponse;
import FlowBank.FlowBank.Cliente.Service.CadastroService;

@RestController 
@RequestMapping("/api")
public class CadastroCliente {
    private final CadastroService service;

    public CadastroCliente(CadastroService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarCliente")
    public ResponseEntity<CadastroClienteResponse> cadastrarCliente(@RequestBody CadastroClienteRequest request){
        CadastroClienteResponse response = service.CadastrarCliente(request);
        return ResponseEntity.status(201).body(response);
    }
    
}
