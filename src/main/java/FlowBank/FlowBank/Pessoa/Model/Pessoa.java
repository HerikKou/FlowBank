
package FlowBank.FlowBank.Pessoa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public 
abstract class Pessoa {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 14, max = 14, message = "O CPF deve conter 11 caracteres")
    @Pattern(
    regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
    message = "O CPF deve estar no formato 000.000.000-00"
)
    private String cpf;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(
    regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*]).+$",
    message = "A senha deve conter letras, números e pelo menos um caractere especial"
)
    private String senha;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String email, String cpf, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.senha = senha;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    

    
}