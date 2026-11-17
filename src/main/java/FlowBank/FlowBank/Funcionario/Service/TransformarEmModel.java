package FlowBank.FlowBank.Funcionario.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioRequest;
import FlowBank.FlowBank.Funcionario.DTO.CadastroFuncionarioResponse;
import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;
import FlowBank.FlowBank.Funcionario.GlobalException.tratarCredenciais;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Funcionario.Repository.FuncionarioRepository;

@Service 
public class TransformarEmModel {
    
    private final FuncionarioRepository repository ;
    private  final PasswordEncoder passwordEncoder;

    
   


    public TransformarEmModel(FuncionarioRepository repository, PasswordEncoder passwordEncoder ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
       
    }
    public Funcionario cadastrofuncionario(CadastroFuncionarioRequest request){
        String senhacriptografada = passwordEncoder.encode(request.senha());
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.nome());
        funcionario.setEmail(request.email());
        funcionario.setCpf(request.cpf());
        funcionario.setSenha(senhacriptografada);
        funcionario.setTipo(request.tipo());
        repository.save(funcionario);
        return  funcionario;
    }
    public Funcionario loginFuncionario(LoginRequest request){
        Funcionario funcionario = repository.findByEmail(request.email()).orElseThrow(()->  new UsernameNotFoundException ("Funcionario nao encontrado"));
        if(!passwordEncoder.matches(request.senha(), funcionario.getSenha())){
            throw new BadCredentialsException("Senha incorreta");
        }
        return  funcionario;
    } 
}
