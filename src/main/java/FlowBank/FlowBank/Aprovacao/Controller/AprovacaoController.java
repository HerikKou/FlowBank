package FlowBank.FlowBank.Aprovacao.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Aprovacao.DTO.AprovacaoResponse;
import FlowBank.FlowBank.Aprovacao.DTO.AprovarSolicitacaoRequest;
import FlowBank.FlowBank.Aprovacao.Service.AprovarService;

@RestController 
@RequestMapping("/api")
public class AprovacaoController {
    private  final AprovarService aprovarService;

    public AprovacaoController(AprovarService aprovarService) {
        this.aprovarService = aprovarService;
    }
    
    @PostMapping("/aprovar")
    public  ResponseEntity<AprovacaoResponse> aprovar(@RequestBody AprovarSolicitacaoRequest request){
        AprovacaoResponse response = aprovarService.aprovarSolicitacao(request);
        return  ResponseEntity.status(200).body(response);
    }
}
