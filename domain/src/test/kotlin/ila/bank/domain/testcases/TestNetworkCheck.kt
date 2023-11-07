package ila.bank.domain.testcases

import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.domain.core.UnitTestUseCases
import ila.bank.domain.param.NoParams
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters



/**
 * Testing the Use-cases for network error
 * */
@RunWith(JUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestNetworkCheck : UnitTestUseCases(false) {


    @Test
    fun `testCase01-(check Network Error)`() {

        val data = executeUseCase {
            getAllCoinsUseCase.buildUseCase(NoParams)
        }

        checkUseCondition("NO_INTERNET", data) { result ->
            if (result is ResultWrapper.Error) {
                return@checkUseCondition result.error is ApiException.NoInternetConnection
            }
            return@checkUseCondition result !is ResultWrapper.Success
        }


    }


}