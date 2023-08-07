package backend.challenge.controller.validator.challenge

import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.shared.controller.validator.AbstractValidator

class ChallengeValidator : AbstractValidator<ChallengeConstraint, ChallengeRequest>() {
    override fun initialize(annotation: ChallengeConstraint) {
        message = localization.i18n(annotation.message)
    }

    override fun checkViolations(value: ChallengeRequest) {
        if (value.start != null && value.end != null && value.start >= value.end) {
            addViolation(template = "exception.validation.timespan")
        }
    }
}
