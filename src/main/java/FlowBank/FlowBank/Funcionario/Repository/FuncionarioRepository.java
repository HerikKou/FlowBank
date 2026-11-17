package FlowBank.FlowBank.Funcionario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import FlowBank.FlowBank.Funcionario.Model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
Optional<Funcionario> findByEmail(String email);
}
