package FlowBank.FlowBank.Solicitacao.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Solicitacao.DTO.CriarSolicitacaoRequest;
import FlowBank.FlowBank.Solicitacao.DTO.SolicitacaoResponse;
import FlowBank.FlowBank.Solicitacao.Service.SolicitacaoService;

@RestController 
@RequestMapping("/api")
public class SolicitacaoController {
    
    private  final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }
    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping("/solicitar")
    public ResponseEntity<SolicitacaoResponse> solicitarLimite(@RequestBody CriarSolicitacaoRequest request){
        SolicitacaoResponse response = solicitacaoService.solicitar(request);
        return ResponseEntity.status(201).body(response);
    }
}
