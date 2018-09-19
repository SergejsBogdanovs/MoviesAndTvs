package lv.st.sbogdano.cinema.navigation

import android.app.Activity
import android.content.Intent
import lv.st.sbogdano.cinema.home.HomeActivity

class Navigator {

    fun navigateToHome(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }
}