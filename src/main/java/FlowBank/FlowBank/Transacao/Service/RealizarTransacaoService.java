package FlowBank.FlowBank.Transacao.Service;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Historico.Service.HistoricoService;
import FlowBank.FlowBank.Transacao.DTO.RealizarTransacaoRequest;
import FlowBank.FlowBank.Transacao.DTO.RealizarTransacaoResponse;
import FlowBank.FlowBank.Transacao.Model.Transacao;
import FlowBank.FlowBank.Transacao.Repository.TransacaoRepository;

@Service 
public class RealizarTransacaoService {
    
    private final ClienteRepository repository;
    private final TransacaoRepository repository2;
    private final TransfomarResponse response;
    private  final HistoricoService service;

    

    public RealizarTransacaoService(ClienteRepository repository, TransacaoRepository repository2,
            TransfomarResponse response, HistoricoService service) {
        this.repository = repository;
        this.repository2 = repository2;
        this.response = response;
        this.service = service;
    }



    public RealizarTransacaoResponse realizarTransacao(RealizarTransacaoRequest request){
        
        Transacao transacao = SalvarTransacao(request);
        return response.Response(transacao);

    }



    private Transacao SalvarTransacao(RealizarTransacaoRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = buscarClientePeloEmail(email);
        Transacao transacao = new Transacao();
        transacao.setCliente(cliente);
        transacao.setCategoria(request.categoria());
        transacao.setDataDeGeracao(LocalDateTime.now());
        transacao.setDataDeTransferencia(LocalDateTime.now());
        transacao.setValor(request.valor());
        Transacao transacaosalva = repository2.save(transacao);
        service.SalvarHistorico(transacaosalva.getId());
        return  transacaosalva;

    }


    private  Cliente buscarClientePeloEmail(String email){
        Cliente cliente = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
        return  cliente;
    }
}
