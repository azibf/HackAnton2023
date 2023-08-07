package backend.shared.controller.validator

import backend.shared.config.localization.Localization
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractValidator<Annotation : kotlin.Annotation, Target> :
    ConstraintValidator<Annotation, Target> {

    @Autowired
    lateinit var localization: Localization

    protected lateinit var context: HibernateConstraintValidatorContext
    protected lateinit var message: String
    private var valid: Boolean = true

    protected abstract fun checkViolations(value: Target)

    protected fun addViolation(vararg parameters: Pair<String, Any>, template: String = message) {
        parameters.forEach { (name, value) -> context.addMessageParameter(name, value) }
        context.buildConstraintViolationWithTemplate(localization.i18n(template))
            .addConstraintViolation()
        valid = false
    }

    override fun isValid(value: Target, ctx: ConstraintValidatorContext): Boolean {
        context = ctx.unwrap(HibernateConstraintValidatorContext::class.java)
        context.disableDefaultConstraintViolation()
        valid = true
        checkViolations(value)
        return valid
    }
}
