package FlowBank.FlowBank.Aprovacao.Service;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Aprovacao.Model.StatusAprovacao;

@Service 
public class TaskServiceFlow {
    private  final TaskService task;



    public TaskServiceFlow(TaskService task) {
        this.task = task;
    }



    public void completarTarefa( Long solicitacaoId,StatusAprovacao status,String justificativa){
        Task tarefa = task.createTaskQuery()
            .taskDefinitionKey("analisarSolicitacao")
            .processVariableValueEquals("solicitacaoId", solicitacaoId)
            .singleResult();
        var tarefas = task.createTaskQuery()
    .taskDefinitionKey("analisarSolicitacao")
    .list();

System.out.println("Total de tarefas: " + tarefas.size());

for (Task t : tarefas) {
    System.out.println("Task ID: " + t.getId());
    System.out.println("Process Instance ID: " + t.getProcessInstanceId());
}
        if (tarefa == null) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        Map<String,Object> variaveis = new HashMap<>();
       
        variaveis.put("justificativa", justificativa);
        variaveis.put("status",status.name());
        task.complete(tarefa.getId(), variaveis);
    }
}
