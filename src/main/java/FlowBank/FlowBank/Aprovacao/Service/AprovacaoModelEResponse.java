package FlowBank.FlowBank.Aprovacao.Service;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Aprovacao.DTO.AprovacaoResponse;
import FlowBank.FlowBank.Aprovacao.DTO.AprovarSolicitacaoRequest;
import FlowBank.FlowBank.Aprovacao.Model.Aprovacao;
import FlowBank.FlowBank.Aprovacao.Repository.AprovacaoRepository;
import FlowBank.FlowBank.Funcionario.DTO.FuncionarioReferenciaResponse;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Funcionario.Repository.FuncionarioRepository;
import FlowBank.FlowBank.Score.DTO.ScoreReferenciaResponse;
import FlowBank.FlowBank.Score.Model.Score;
import FlowBank.FlowBank.Score.Repository.ScoreRepository;
import FlowBank.FlowBank.Solicitacao.DTO.SolicitacaoReferenciaResponse;
import FlowBank.FlowBank.Solicitacao.Model.Solicitacao;
import FlowBank.FlowBank.Solicitacao.Repository.SolicitacaoRepository;

@Service 
public class AprovacaoModelEResponse {
    private final FuncionarioRepository funcionarioRepository;
    private  final ScoreRepository scoreRepository;
    private  final AprovacaoRepository aprovacaoRepository;
    private  final SolicitacaoRepository solicitacaoRepository;
    
    public AprovacaoModelEResponse(FuncionarioRepository funcionarioRepository, ScoreRepository scoreRepository,
            AprovacaoRepository aprovacaoRepository, SolicitacaoRepository solicitacaoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.scoreRepository = scoreRepository;
        this.aprovacaoRepository = aprovacaoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public Aprovacao entidade(AprovarSolicitacaoRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Funcionario funcionario = funcionarioRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("usuario não encontrado"));
       Solicitacao solicitacao = solicitacaoRepository.findById(request.solicitacaoId()).orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
       Score score = scoreRepository.findTopByHistorico_Cliente_IdOrderByDataAnaliseDesc(solicitacao.getCliente().getId()).orElseThrow(()-> new UsernameNotFoundException("Usuario nao encontrado"));
       Aprovacao aprovacao = new Aprovacao();
       aprovacao.setFuncionario(funcionario);
       aprovacao.setSolicitacao(solicitacao);
       aprovacao.setScore(score);
       aprovacao.setJustificativa(request.justificativa());
       aprovacao.setStatus(request.status());
       Aprovacao aprovacaosalva = aprovacaoRepository.save(aprovacao);
       return aprovacaosalva;
    }
    public AprovacaoResponse response(Aprovacao aprovacao){
        FuncionarioReferenciaResponse funcionarioReferenciaResponse = new FuncionarioReferenciaResponse(aprovacao.getFuncionario().getId(),aprovacao.getFuncionario().getNome(),aprovacao.getFuncionario().getTipo(),aprovacao.getFuncionario().getEmail());
        ScoreReferenciaResponse scoreReferenciaResponse = new ScoreReferenciaResponse(aprovacao.getScore().getId(),aprovacao.getScore().getScoreFinal(),aprovacao.getScore().getRisco());
        SolicitacaoReferenciaResponse solicitacaoReferenciaResponse = new SolicitacaoReferenciaResponse(aprovacao.getSolicitacao().getId(),aprovacao.getSolicitacao().getLimiteSolcitado(),aprovacao.getSolicitacao().getMotivo());
        return new AprovacaoResponse(aprovacao.getId(),aprovacao.getStatus(),aprovacao.getDataAprovacao(),aprovacao.getJustificativa(), funcionarioReferenciaResponse,solicitacaoReferenciaResponse,scoreReferenciaResponse);
    }
}
