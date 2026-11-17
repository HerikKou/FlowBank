package FlowBank.FlowBank.Aprovacao.Delegate;

import org.springframework.stereotype.Component;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.engine.delegate.DelegateExecution;

import FlowBank.FlowBank.Aprovacao.DTO.AprovarSolicitacaoRequest;

import FlowBank.FlowBank.Aprovacao.Model.StatusAprovacao;
import FlowBank.FlowBank.Aprovacao.Service.AprovarService;
import FlowBank.FlowBank.Aprovacao.Service.TaskServiceFlow;

@Component
public class aprovacaoDelegate implements JavaDelegate {

    private final AprovarService aprovacaoService;
    
   

  


    public aprovacaoDelegate(AprovarService aprovacaoService) {
        this.aprovacaoService = aprovacaoService;
        
    }





    @Override
    public void execute(DelegateExecution execution) {
        Long solicitacaoId =(Long)execution.getVariable("solicitacaoId");
        StatusAprovacao status =
        StatusAprovacao.valueOf(
            (String) execution.getVariable("status")
        );

    String justificativa =
        (String) execution.getVariable("justificativa");

    AprovarSolicitacaoRequest request =
        new AprovarSolicitacaoRequest(
            solicitacaoId,
            status,
            justificativa
        );
        

    aprovacaoService.aprovarSolicitacao(request);
}
    }
