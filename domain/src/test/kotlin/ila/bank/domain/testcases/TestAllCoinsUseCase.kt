package ila.bank.domain.testcases

import ila.bank.data.core.ResultWrapper
import ila.bank.data.source.DataSourceMode
import ila.bank.domain.core.UnitTestUseCases
import ila.bank.domain.param.AllCoinsParams
import ila.bank.domain.param.NoParams
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters



/**
 * Testing the Use-cases for fetching all coins api
 * */
@RunWith(JUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestAllCoinsUseCase : UnitTestUseCases(
    true,
    DataSourceMode.PROXY
) {


    /***
     * Testing the happy scenario of all coins api
     * */
    @Test
    fun `testCase02-(get all coins)`() {

        val data = executeUseCase {
            getAllCoinsUseCase.buildUseCase(AllCoinsParams(0))
        }

        checkUseCondition("All Coins found!", data) { result ->
            if (result is ResultWrapper.Success) {
                return@checkUseCondition result.value.isNotEmpty()
            }
            return@checkUseCondition false
        }

    }


}