package FlowBank.FlowBank.Historico.Service;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.ClienteReferenciaResponse;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Historico.DTO.HistoricoResponse;
import FlowBank.FlowBank.Historico.Model.Historico;
import FlowBank.FlowBank.Historico.Repository.HistoricoRepository;
import FlowBank.FlowBank.Score.Service.ScoreService;
import FlowBank.FlowBank.Transacao.DTO.TransacaoReferenciaResponse;
import FlowBank.FlowBank.Transacao.Model.Transacao;
import FlowBank.FlowBank.Transacao.Repository.TransacaoRepository;

@Service 
public class HistoricoService {
    private final HistoricoRepository historicoRepository;
    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;
    private final ScoreService scoreService;
    
    


    public HistoricoService(HistoricoRepository historicoRepository, ClienteRepository clienteRepository,
            TransacaoRepository transacaoRepository, ScoreService scoreService) {
        this.historicoRepository = historicoRepository;
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
        this.scoreService = scoreService;
    }
    private Cliente buscarCliente(String email){
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuario não encontrado"));
        return cliente;
    }
    private Transacao buscarTransacao(long transacaoId){
        Transacao transacao = transacaoRepository.findById(transacaoId).orElseThrow(()-> new RuntimeException("Transacao não encontrada"));
        return  transacao;
    }
    public  HistoricoResponse SalvarHistorico(long transacaoId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = buscarCliente(email);
        Transacao transacao = buscarTransacao(transacaoId);
        Historico historico = new Historico();
        historico.setCliente(cliente);
        historico.setTransacao(transacao);
        historico.setDataRegistro(LocalDateTime.now());
        Historico historicosalvo = historicoRepository.save(historico);
        scoreService.salvarScore(historicosalvo.getId());
        return response(historicosalvo);

    }
    private HistoricoResponse response(Historico historico){
        ClienteReferenciaResponse clienteReferencia = new ClienteReferenciaResponse(historico.getCliente().getId(),historico.getCliente().getNome(),historico.getCliente().getEmail());
        TransacaoReferenciaResponse referenciaResponse = new TransacaoReferenciaResponse(historico.getTransacao().getId(),historico.getTransacao().getValor(),historico.getTransacao().getCategoria(),historico.getTransacao().getDataDeGeracao());
        return new HistoricoResponse(
            historico.getId(),
            historico.getDataRegistro(),
            clienteReferencia,
            referenciaResponse
        );    
    }
}
