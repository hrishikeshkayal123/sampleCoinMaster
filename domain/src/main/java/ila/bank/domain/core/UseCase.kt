package ila.bank.domain.core

import ila.bank.data.core.ApiException
import ila.bank.data.core.ApiException.Companion.mapToApiException
import ila.bank.data.core.ResultWrapper



/**
 * Generic use-cases logic for apis
 * */
abstract class UseCase<Params, ApiResponse, Result> {

    abstract suspend fun buildUseCase(params: Params): ResultWrapper<ApiException, Result>

    /***
     * segregation of result wrapper result
     * */
    protected suspend fun execute(task: suspend () -> ResultWrapper<Throwable, ApiResponse>)
            : ResultWrapper<ApiException, Result> {
        val result = task.invoke()
        if (result is ResultWrapper.Success)
            return ResultWrapper.Success(mapResponse(result.value))

        return ResultWrapper.Error((result as ResultWrapper.Error).error.mapToApiException())
    }

    /***
     * mapping the logic of api response to particular result
     */
    abstract fun mapResponse(response: ApiResponse): Result


}