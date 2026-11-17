package FlowBank.FlowBank.Aprovacao.Service;

import java.util.HashMap;
import java.util.Map;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;

@Service
public class AprovacaoWorkflowService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public AprovacaoWorkflowService(
            RuntimeService runtimeService,
            TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    public void iniciarProcesso(Long solicitacaoId) {

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("solicitacaoId", solicitacaoId);

        ProcessInstance processo =
            runtimeService.startProcessInstanceByKey(
                "aprovacaoLimite",
                variaveis
            );

        System.out.println("Processo iniciado: " + processo.getId());

        var tarefas = taskService.createTaskQuery()
            .processInstanceId(processo.getId())
            .list();

        System.out.println("Tarefas criadas: " + tarefas.size());

        for (Task tarefa : tarefas) {
            System.out.println("Tarefa: " + tarefa.getName());
            System.out.println("Task Definition Key: " + tarefa.getTaskDefinitionKey());
        }
         
    }
}
