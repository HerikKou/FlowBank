package FlowBank.FlowBank.Aprovacao.DTO;

import FlowBank.FlowBank.Aprovacao.Model.StatusAprovacao;
import jakarta.validation.constraints.NotNull;

public record ConcluirTarefaRequest(
    @NotNull
    StatusAprovacao status,

    String justificativa
) {}