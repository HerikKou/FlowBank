package FlowBank.FlowBank.Cliente.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import FlowBank.FlowBank.Cliente.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
Optional<Cliente> findByEmail(String email);
}