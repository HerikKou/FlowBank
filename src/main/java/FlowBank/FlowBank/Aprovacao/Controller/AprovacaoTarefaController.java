package FlowBank.FlowBank.Aprovacao.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlowBank.FlowBank.Aprovacao.DTO.ConcluirTarefaRequest;
import FlowBank.FlowBank.Aprovacao.Service.TaskServiceFlow;

@RestController
@RequestMapping("/api/solicitacoes")
public class AprovacaoTarefaController {

    private final TaskServiceFlow taskServiceFlow;

    public AprovacaoTarefaController(TaskServiceFlow taskServiceFlow) {
        this.taskServiceFlow = taskServiceFlow;
    }

    @PostMapping("/{solicitacaoId}/decisao")
    public ResponseEntity<Void> concluirTarefa(
        @PathVariable Long solicitacaoId,
        @RequestBody ConcluirTarefaRequest request
    ) {

        taskServiceFlow.completarTarefa(
            solicitacaoId,
            request.status(),
            request.justificativa()
        );

        return ResponseEntity.noContent().build();
    }
}