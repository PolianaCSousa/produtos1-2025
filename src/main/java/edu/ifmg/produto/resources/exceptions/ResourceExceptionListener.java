package edu.ifmg.produto.resources.exceptions;

import edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //rever com alguem depois, não consegui prestar atenção na aula :(
public class ResourceExceptionListener { //posso trocar listener por handler e essa classe conforme mais erros surgirem e eu precisar tratá-los

    @ExceptionHandler(ResourceNotFound.class) //quando uma excessão do tipo Resource not found acontecer o java vai acionar o metodo abaixo
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFound ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError error = new StandartError();
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Resource not found");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
