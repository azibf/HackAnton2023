package backend.challenge.model.exception

import backend.shared.model.exception.AbstractApiException
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import feign.FeignException
import org.springframework.http.HttpStatus

class FeignApiException(
    exception: FeignException.FeignClientException,
) : AbstractApiException(
    code = HttpStatus.BAD_REQUEST,
    message = Message.build(exception.contentUTF8()).message,
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Message(
        val message: String,
    ) {
        companion object {
            fun build(content: String) = jacksonObjectMapper().readValue<Message>(content)
        }
    }
}
