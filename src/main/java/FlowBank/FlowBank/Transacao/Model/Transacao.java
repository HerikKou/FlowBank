package FlowBank.FlowBank.Transacao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Historico.Model.Historico;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor da transação é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "A categoria do gasto é obrigatória")
    @Enumerated(EnumType.STRING)
    private CategoriaGasto categoria;

    @NotNull(message = "A data de geração é obrigatória")
    private LocalDateTime dataDeGeracao = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente é obrigatório")
     private Cliente cliente;
    @NotNull(message = "A data de transferência é obrigatória")
    private LocalDateTime dataDeTransferencia;

    @OneToMany(mappedBy = "transacao")
    @NotNull(message = "O Historico é obrigatório")
    private List<Historico> historicos = new ArrayList<>();

    public Transacao() {}

    public Transacao(Long id, @NotNull(message = "O valor da transação é obrigatório") BigDecimal valor,
            @NotNull(message = "A categoria do gasto é obrigatória") CategoriaGasto categoria,
            @NotNull(message = "A data de geração é obrigatória") LocalDateTime dataDeGeracao,
            @NotNull(message = "O cliente é obrigatório") Cliente cliente,
            @NotNull(message = "A data de transferência é obrigatória") LocalDateTime dataDeTransferencia,
            @NotNull(message = "O Historico é obrigatório") List<Historico> historicos) {
        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.dataDeGeracao = dataDeGeracao;
        this.cliente = cliente;
        this.dataDeTransferencia = dataDeTransferencia;
        this.historicos = historicos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CategoriaGasto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaGasto categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataDeGeracao() {
        return dataDeGeracao;
    }

    public void setDataDeGeracao(LocalDateTime dataDeGeracao) {
        this.dataDeGeracao = dataDeGeracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataDeTransferencia() {
        return dataDeTransferencia;
    }

    public void setDataDeTransferencia(LocalDateTime dataDeTransferencia) {
        this.dataDeTransferencia = dataDeTransferencia;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<Historico> historicos) {
        this.historicos = historicos;
    }

    
   
    
}