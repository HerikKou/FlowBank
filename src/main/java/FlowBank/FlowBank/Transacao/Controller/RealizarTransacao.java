package FlowBank.FlowBank.Transacao.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Transacao.DTO.RealizarTransacaoRequest;
import FlowBank.FlowBank.Transacao.DTO.RealizarTransacaoResponse;
import FlowBank.FlowBank.Transacao.Service.RealizarTransacaoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api")
public class RealizarTransacao {
    
    private final RealizarTransacaoService service;
    
    public RealizarTransacao(RealizarTransacaoService service) {
        this.service = service;
    }
     @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping("/realizarTransacao")
    public ResponseEntity<RealizarTransacaoResponse> realizarTransacaoController(@Valid @RequestBody RealizarTransacaoRequest request){
        RealizarTransacaoResponse response = service.realizarTransacao(request);
        return ResponseEntity.status(200).body(response);
    }
}
