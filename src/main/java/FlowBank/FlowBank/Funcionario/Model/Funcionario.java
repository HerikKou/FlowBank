package FlowBank.FlowBank.Funcionario.Model;

import java.util.ArrayList;
import java.util.List;

import FlowBank.FlowBank.Pessoa.Model.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import FlowBank.FlowBank.Aprovacao.Model.*;
@Entity 
public class Funcionario  extends Pessoa{
    @NotNull(message = "O tipo do funcionario é obrigatorio")
    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipo;
    @OneToMany(mappedBy = "funcionario")
    private List<Aprovacao> aprovacoes = new ArrayList<>();
    public Funcionario(){}
    public Funcionario(Long id, String nome, String email, String cpf, String senha,
            @NotNull(message = "O tipo do funcionario é obrigatorio") TipoFuncionario tipo) {
        super(id, nome, email, cpf, senha);
        this.tipo = tipo;
    }
    public TipoFuncionario getTipo() {
        return tipo;
    }
    public void setTipo(TipoFuncionario tipo) {
        this.tipo = tipo;
    }
    public List<Aprovacao> getAprovacoes() {
        return aprovacoes;
    }
    public void setAprovacoes(List<Aprovacao> aprovacoes) {
        this.aprovacoes = aprovacoes;
    }
    

}
