package FlowBank.FlowBank.Aprovacao.DTO;

import FlowBank.FlowBank.Aprovacao.Model.StatusAprovacao;
import jakarta.validation.constraints.NotNull;

public record AprovarSolicitacaoRequest(
    @NotNull
    Long solicitacaoId,

    @NotNull
    StatusAprovacao status,

    String justificativa
) {}
