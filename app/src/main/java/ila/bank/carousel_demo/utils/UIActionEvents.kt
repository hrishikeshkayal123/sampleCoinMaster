package ila.bank.carousel_demo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast




/**
 * Common UI functionalities such toast, navigation of activity , hideKeyboard, etc
 */

fun Context?.showToast(message: String?) {
    this?.let {
        Toast.makeText(it, message, Toast.LENGTH_LONG).show()
    }
}


fun Activity?.navigateToActivity(clazz: Class<*>, autoFinish: Boolean = false) {
    this?.let {
        startActivity(Intent(it, clazz))
        if (autoFinish) {
            finish()
        }
    }
}


fun Activity?.hideKeyboard() {
    this?.currentFocus?.let {

        val manager =
            this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        manager
            .hideSoftInputFromWindow(
                it.windowToken, 0
            )
    }

}