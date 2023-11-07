package ila.bank.domain.testcases

import ila.bank.data.core.ResultWrapper
import ila.bank.data.source.DataSourceMode
import ila.bank.domain.core.UnitTestUseCases
import ila.bank.domain.param.CoinDetailsParams
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters



/**
 * Testing the Use-cases for fetching coins detail api
 * */
@RunWith(JUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestCoinDetailsUseCase : UnitTestUseCases(
    true,
    dataSourceMode = DataSourceMode.PROXY
) {

    /***
     * Testing the happy scenario of coins detail api
     * */
    @Test
    fun `testCase03-(coin details)`() {

        val data = executeUseCase {
            getCoinsDetailUseCase.buildUseCase(
                params = CoinDetailsParams(currency = "usd")
            )
        }

        checkUseCondition("Coin Detail found!", data) { result ->
            if (result is ResultWrapper.Success) {
                return@checkUseCondition result.value.isNotEmpty()
            }
            return@checkUseCondition false
        }

    }


}