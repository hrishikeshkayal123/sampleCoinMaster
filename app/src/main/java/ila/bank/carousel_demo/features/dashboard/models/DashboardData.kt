package ila.bank.carousel_demo.features.dashboard.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ila.bank.carousel_demo.BR


/**
 * DashboardData is UI data binding model on UI
 * */
class DashboardData : BaseObservable() {


    @Bindable
    var showLoader = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoader)
        }
}