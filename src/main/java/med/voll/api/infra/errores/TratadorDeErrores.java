package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity tratarErrorValidacionesDeIntegridad(ValidacionDeIntegridad e) {
        return ResponseEntity.badRequest().body(new DatosErrorValidacion(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErrorValidacionesDeNegocio(ValidationException e) {
        return ResponseEntity.badRequest().body(new DatosErrorValidacion(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosError400::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record DatosError400(String campo, String error) {
        DatosError400(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record DatosErrorValidacion(String error) {}
}
