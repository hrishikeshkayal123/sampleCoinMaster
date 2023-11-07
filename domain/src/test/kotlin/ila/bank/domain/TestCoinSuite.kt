package ila.bank.domain

import ila.bank.domain.testcases.TestAllCoinsUseCase
import ila.bank.domain.testcases.TestCoinDetailsUseCase
import ila.bank.domain.testcases.TestNetworkCheck
import org.junit.runner.RunWith
import org.junit.runners.Suite




/**
 * Testing the All Use-cases at one time
 * */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TestNetworkCheck::class,
    TestAllCoinsUseCase::class,
    TestCoinDetailsUseCase::class
)
class TestCoinSuite
