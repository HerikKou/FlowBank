package FlowBank.FlowBank.Transacao.Service;

import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Transacao.DTO.RealizarTransacaoResponse;
import FlowBank.FlowBank.Transacao.Model.Transacao;

@Service 
public class TransfomarResponse {
    
    public RealizarTransacaoResponse Response(Transacao transacao){
        return  new RealizarTransacaoResponse(transacao.getId(),transacao.getValor(), transacao.getCategoria(), transacao.getCliente().getId());
    }
}
