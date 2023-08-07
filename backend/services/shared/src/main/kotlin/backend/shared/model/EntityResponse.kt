package backend.shared.model

import java.util.UUID

interface EntityResponse : ApiResponse {
    val id: UUID
}
