package udb.edu.sv.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("error", "No se puede modificar este elemento debido a restricciones de integridad de datos.");
        return "general/utils/customError";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", "No tiene permisos para acceder.");
        return "general/utils/customError";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException ex, Model model) {
        model.addAttribute("error", "No hemos encontrado los que buscas.");
        return "general/utils/customError";
    }
}
