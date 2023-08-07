package backend.shared.model.exception

import jakarta.validation.ConstraintViolation
import org.springframework.http.HttpStatus

class ValidationException(
    constraints: Set<ConstraintViolation<*>>,
) : AbstractApiException(
    code = HttpStatus.BAD_REQUEST,
    message = "exception.validation.error",
) {
    class ValidationError(
        val field: String,
        val rejectedValue: Any?,
        val description: String? = null,
    )

    val validation = constraints.map { violation ->
        ValidationError(
            field = violation.propertyPath.toString(),
            rejectedValue = violation.invalidValue,
            description = violation.message,
        )
    }
}
