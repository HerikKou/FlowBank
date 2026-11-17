package FlowBank.FlowBank.Aprovacao.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Aprovacao.DTO.AprovacaoResponse;
import FlowBank.FlowBank.Aprovacao.DTO.AprovarSolicitacaoRequest;
import FlowBank.FlowBank.Aprovacao.Model.Aprovacao;

@Service 
public class AprovarService {
    
    private final AprovacaoModelEResponse aprovacaoModelEResponse;

    public AprovarService(AprovacaoModelEResponse aprovacaoModelEResponse) {
        this.aprovacaoModelEResponse = aprovacaoModelEResponse;
    }
    public AprovacaoResponse aprovarSolicitacao(AprovarSolicitacaoRequest request){
        Aprovacao aprovacao = aprovacaoModelEResponse.entidade(request);
        AprovacaoResponse aprovacaoResponse = aprovacaoModelEResponse.response(aprovacao);
        return  aprovacaoResponse;
    }
}
