package FlowBank.FlowBank.Aprovacao.DTO;

import java.time.LocalDateTime;

import FlowBank.FlowBank.Aprovacao.Model.StatusAprovacao;
import FlowBank.FlowBank.Funcionario.DTO.FuncionarioReferenciaResponse;
import FlowBank.FlowBank.Score.DTO.ScoreReferenciaResponse;
import FlowBank.FlowBank.Solicitacao.DTO.SolicitacaoReferenciaResponse;

public record AprovacaoResponse(

    Long id,

    StatusAprovacao status,

    LocalDateTime dataAprovacao,

    String justificativa,

    FuncionarioReferenciaResponse funcionario,

    SolicitacaoReferenciaResponse solicitacao,

    ScoreReferenciaResponse score

) {}