package ila.bank.carousel_demo.features.start



/**
 * SplashEvents is set of sealed events which will be observe form VM
 * */
sealed class SplashEvents {

    object Init : SplashEvents()
    object NavigateToDashBoard : SplashEvents()

}