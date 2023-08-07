package backend.shared.model

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class PageRequest(
    val page: Int,
    val size: Int,
    val order: Sort.Direction,
    val field: String,
) {
    val pageable = PageRequest.of(page - 1, size, order, field)
}
