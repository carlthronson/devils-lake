package personal.carlthronson.dl.be.ctrl;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SimpleController {

    /**
     * Constraint violation exception handler
     * 
     * @param ex ConstraintViolationException
     * @return List<ValidationError> - list of ValidationError built
     *         from set of ConstraintViolation
     */
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(Exception.class)
//    public StatusSaveResponseBody handleException(Exception ex) {
//        StatusSaveResponseBody response = new StatusSaveResponseBody();
//        response.setMessage(ex.getMessage());
//        return response;
//    }

//        handleConstraintViolation(ConstraintViolationException ex) {
////            log.debug(
////                "Constraint violation exception encountered: {}",
////                ex.getConstraintViolations(),       
////                ex
////            );
//        return ex.getConstraintName();
//    }

//    /**
//     * Build list of ValidationError from set of ConstraintViolation
//     *
//     * @param violations Set<ConstraintViolation<?>> - Set 
//     * of parameterized ConstraintViolations
//     * @return List<ValidationError> - list of validation errors
//     */
//    private List<ValidationError>  buildValidationErrors(
//       Set<ConstraintViolation<?>> violations) {
//        return violations.
//               stream().
//               map(violation -> 
//               ValidationError.builder().
//               field(
//                  StreamSupport.stream(
//                  violation.getPropertyPath().spliterator(), false).
//                  reduce((first, second) -> second).
//                  orElse(null).
//                  toString()
//               ).
//               error(violation.getMessage()).
//               build()).
//               collect(toList());
//      }
//   }
}
