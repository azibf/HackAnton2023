package backend.shared.config.localization

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Validation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.Locale

@Configuration
class InternationalizationConfig {
    private val defaultLanguage = Locale("ru")
    private val locales = listOf(
        Locale("ru"),
        Locale("en"),
    )

    @Bean
    fun messageSource() = ResourceBundleMessageSource().apply {
        setBasenames("messages", "shared/messages")
        setDefaultEncoding("UTF-8")
        setUseCodeAsDefaultMessage(true)
    }

    @Bean
    fun localeResolver() = object : AcceptHeaderLocaleResolver() {
        override fun resolveLocale(request: HttpServletRequest): Locale {
            val header = request.getHeader("Accept-Language") ?: return defaultLanguage
            val list = Locale.LanguageRange.parse(header)
            return Locale.lookup(list, locales) ?: defaultLanguage
        }
    }

    @Bean
    fun validator(): LocalValidatorFactoryBean {
        val defaultInterpolator = Validation.byDefaultProvider().configure().defaultMessageInterpolator
        return LocalValidatorFactoryBean().apply {
            setValidationMessageSource(messageSource())
            messageInterpolator = CustomMessageInterpolator(defaultInterpolator)
        }
    }
}
