package FlowBank.FlowBank.Funcionario.Service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import FlowBank.FlowBank.Funcionario.Model.Funcionario;
import FlowBank.FlowBank.Funcionario.Model.TipoFuncionario;
import FlowBank.FlowBank.Funcionario.Repository.FuncionarioRepository;


@Component
public class DataSeeder implements CommandLineRunner {

    @Value("${app.gerente-inicial.email}")
    private String emailGerente;
    @Value("${app.gerente-inicial.cpf}")
    private String cpf;
    @Value("${app.gerente-inicial.senha}")
    private String senhaGerente;

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            FlowBank.FlowBank.Funcionario.Repository.FuncionarioRepository funcionarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

            funcionarioRepository.findByEmail(emailGerente)
        .ifPresentOrElse(
            gerente -> System.out.println("Gerente já cadastrado!"),
            () -> {
                Funcionario gerente = new Funcionario();

                gerente.setNome("Administrador");
                gerente.setEmail(emailGerente);
                gerente.setSenha(passwordEncoder.encode(senhaGerente));
                gerente.setCpf(cpf);
                gerente.setTipo(TipoFuncionario.GERENTE);

                funcionarioRepository.save(gerente);

                System.out.println("Gerente inicial criado!");
            }
        );

    }
}