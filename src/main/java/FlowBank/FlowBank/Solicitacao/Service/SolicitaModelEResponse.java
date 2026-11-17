package FlowBank.FlowBank.Solicitacao.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Aprovacao.Service.AprovacaoWorkflowService;
import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Solicitacao.DTO.CriarSolicitacaoRequest;
import FlowBank.FlowBank.Solicitacao.DTO.SolicitacaoResponse;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;
import FlowBank.FlowBank.Solicitacao.Repository.SolicitacaoRepository;

@Service 
public class SolicitaModelEResponse {
    
    private final ClienteRepository repository;
    private  final SolicitacaoRepository solicitacaoRepository;
    private final AprovacaoWorkflowService workflowService;
    

    public SolicitaModelEResponse(ClienteRepository repository, SolicitacaoRepository solicitacaoRepository,
            AprovacaoWorkflowService workflowService) {
        this.repository = repository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.workflowService = workflowService;
    }
    public Solicitacao transformaEntidade(CriarSolicitacaoRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setCliente(cliente);
        solicitacao.setLimiteSolcitado(request.limiteSolicitado());
        solicitacao.setMotivo(request.motivo());
        Solicitacao solicitacaoSalva = solicitacaoRepository.save(solicitacao);
        workflowService.iniciarProcesso(solicitacaoSalva.getId());
        return  solicitacaoSalva;
    }
    public SolicitacaoResponse response(Solicitacao solicitacao){
        ClienteReferenciaResponse clienteReferenciaResponse = new ClienteReferenciaResponse(solicitacao.getCliente().getId(),solicitacao.getCliente().getNome(),solicitacao.getCliente().getEmail());
        SolicitacaoResponse response =  new SolicitacaoResponse(solicitacao.getId(),solicitacao.getLimiteSolcitado(),solicitacao.getMotivo(),clienteReferenciaResponse);
        return response;
    }
}
