package FlowBank.FlowBank.Solicitacao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import FlowBank.FlowBank.Cliente.Model.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Solicitacao {
    @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente é obrigatório")
     private Cliente cliente;
     
    @NotNull(message = "O limite solicitado é obrigatório")
     private BigDecimal limiteSolcitado;

     @NotBlank(message = "O motivo da solicitação é obrigatório")
     private String motivo;
    public Solicitacao(){}
     public Solicitacao(Long id, Cliente cliente,  BigDecimal limiteSolcitado,  String motivo) {
        this.id = id;
        this.cliente = cliente;
        this.limiteSolcitado = limiteSolcitado;
        this.motivo = motivo;
     }
     public Long getId() {
         return id;
     }
     public void setId(Long id) {
         this.id = id;
     }
     public Cliente getCliente() {
         return cliente;
     }
     public void setCliente(Cliente cliente) {
         this.cliente = cliente;
     }
     public BigDecimal getLimiteSolcitado() {
         return limiteSolcitado;
     }
     public void setLimiteSolcitado(BigDecimal limiteSolcitado) {
         this.limiteSolcitado = limiteSolcitado;
     }
     public String getMotivo() {
         return motivo;
     }
     public void setMotivo(String motivo) {
         this.motivo = motivo;
     }

     
     
}
