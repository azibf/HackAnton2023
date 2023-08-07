package backend.shared.model

class ListResponse<T>(
    val content: List<T>,
) : ApiResponse {

    companion object {
        fun <T, R> build(items: Iterable<T>, asResponse: (element: T) -> R) = ListResponse(
            content = items.map { asResponse(it) },
        )
    }
}
