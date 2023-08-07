package backend.shared.config.localization

import jakarta.validation.MessageInterpolator
import java.util.Locale

class CustomMessageInterpolator(
    private val interpolator: MessageInterpolator,
) : MessageInterpolator {

    override fun interpolate(template: String?, context: MessageInterpolator.Context?): String {
        return interpolator.interpolate(template, context)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    override fun interpolate(template: String?, context: MessageInterpolator.Context?, locale: Locale?): String {
        return interpolator.interpolate(template, context, locale)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}
