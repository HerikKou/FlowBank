package FlowBank.FlowBank.Cliente.Service;

import java.math.BigDecimal;


import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import FlowBank.FlowBank.Cliente.DTO.CadastroClienteRequest;
import FlowBank.FlowBank.Cliente.Model.Cliente;
import FlowBank.FlowBank.Cliente.Repository.ClienteRepository;
import FlowBank.FlowBank.Config.Bycrypt;
import FlowBank.FlowBank.Funcionario.DTO.LoginRequest;

@Service 
public class TransformarModel {
    private final PasswordEncoder encoder;
    private final ClienteRepository repository;
    private final Logger log = LoggerFactory.getLogger(TransformarModel.class);
       private final BigDecimal limiteAtual = new BigDecimal(100.00);


    public TransformarModel(PasswordEncoder encoder, ClienteRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }


    public Cliente TransformarModel(CadastroClienteRequest request){
        String senhaCriptografada = encoder.encode(request.senha());
       Cliente cliente = new Cliente();
       cliente.setNome(request.nome());
       cliente.setEmail(request.email());
       cliente.setCpf(request.cpf());
       cliente.setSenha(senhaCriptografada);
       cliente.setLimiteAtual(limiteAtual);
     
       repository.save(cliente);
       return cliente;
    }

    public Cliente TransformarModel(LoginRequest request){
      Cliente cliente = repository.findByEmail(request.email()).orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
       
    if(!encoder.matches(request.senha(),cliente.getSenha())){
        throw new BadCredentialsException("Senha incorreta");
    }
       return cliente;
    }
}
