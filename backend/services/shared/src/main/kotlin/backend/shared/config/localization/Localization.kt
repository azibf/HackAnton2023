package backend.shared.config.localization

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component

@Component
class Localization(
    private val source: ResourceBundleMessageSource,
) {
    fun i18n(label: String, vararg args: String?): String {
        val locale = LocaleContextHolder.getLocale()
        return source.getMessage(label, args, locale)
    }
}
