package backend.shared.model.message

import backend.shared.model.ApiResponse
import com.fasterxml.jackson.annotation.JsonInclude

interface ApiMessage<T> : ApiResponse {
    val message: String

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val data: T?
}
