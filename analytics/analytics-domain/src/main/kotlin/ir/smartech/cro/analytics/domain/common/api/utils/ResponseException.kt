package ir.smartech.cro.analytics.domain.common.api.utils

class ResponseException(status: Int, message: String) : RuntimeException() {
    var status: Int? = null
    override var message: String? = null

    init {
        this.status = status
        this.message = message
    }

    companion object {
        fun newResponseException(stats: Int, message: String): ResponseException {
            return ResponseException(stats, message)
        }
    }
}