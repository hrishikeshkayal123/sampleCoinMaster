package ila.bank.data.core

import com.google.gson.Gson
import ila.bank.data.models.response.ErrorModel
import java.io.IOException



sealed class ApiException(val resCode: Int, open val errorMsg: String? = null) : IOException() {

    /**
     *
     * creating an customise Exception from IO Exception occurs on process of network call
     * such client/server error and we can map other customise Exception based on @{resCode}
     * we can map our bad request exception here and also we have add Unauthorized exception
     * */
    override val message: String?
        get() = errorMsg

    object NoInternetConnection : ApiException(-1,"NO_INTERNET")

    object InternalServerException : ApiException(500)

    data class BadRequestException(val error: String?) : ApiException(400, error) {
        override val errorMsg: String?
            get() = getErrorModelMsg()

        private fun getErrorModelMsg(): String? {
            return try {
                val errorModel: ErrorModel? = Gson().fromJson(error, ErrorModel::class.java)
                return errorModel?.error
            } catch (e: Exception) {
                e.printStackTrace()
                error
            }
        }
    }

    data class UnknownException(
        var code: Int = 999,
        val error: String? = "Unknown Exception"
    ) :
        ApiException(code, error)


    companion object {

        fun Throwable?.mapToApiException(): ApiException {

            var apiException: ApiException? = UnknownException()

            if (this == null) return apiException!!


            if (this.cause is ApiException) {
                apiException = this.cause as ApiException?
            }
            if (apiException is UnknownException && this.cause?.cause is ApiException) {
                apiException = this.cause?.cause as ApiException?
            }

            return apiException ?: UnknownException()
        }


    }

}
