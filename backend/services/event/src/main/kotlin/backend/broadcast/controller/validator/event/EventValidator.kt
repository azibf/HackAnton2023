package backend.broadcast.controller.validator.event

import backend.broadcast.model.dto.EventRequest
import backend.shared.controller.validator.AbstractValidator

class EventValidator : AbstractValidator<EventConstraint, EventRequest>() {
    override fun initialize(annotation: EventConstraint) {
        message = localization.i18n(annotation.message)
    }

    override fun checkViolations(value: EventRequest) {
        if (value.start >= value.end) addViolation()
    }
}
