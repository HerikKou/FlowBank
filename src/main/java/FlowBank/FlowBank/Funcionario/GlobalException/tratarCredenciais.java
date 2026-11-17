package FlowBank.FlowBank.Funcionario.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class tratarCredenciais{
        
@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<String> tratarCredenciaisInvalidas(
        BadCredentialsException ex) {

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ex.getMessage());
}

@ExceptionHandler(UsernameNotFoundException.class)
public ResponseEntity<String> tratarUsuarioNaoEncontrado(
        UsernameNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
}
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<String> tratarClienteNaoEncontrado(
        UsernameNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
}
}


 

