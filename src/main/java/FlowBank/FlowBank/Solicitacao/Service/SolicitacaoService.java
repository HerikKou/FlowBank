package FlowBank.FlowBank.Solicitacao.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Solicitacao.DTO.CriarSolicitacaoRequest;
import FlowBank.FlowBank.Solicitacao.DTO.SolicitacaoResponse;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;
import FlowBank.FlowBank.Solicitacao.Repository.SolicitacaoRepository;


@Service 
public class SolicitacaoService {
    
    private final SolicitaModelEResponse solicitaModelEResponse;
    

    public SolicitacaoService(SolicitaModelEResponse solicitaModelEResponse) {
        this.solicitaModelEResponse = solicitaModelEResponse;
    }


    public SolicitacaoResponse solicitar(CriarSolicitacaoRequest request){
        Solicitacao solicitacao = solicitaModelEResponse.transformaEntidade(request);
        SolicitacaoResponse response = solicitaModelEResponse.response(solicitacao);
        return  response;
    }
}
