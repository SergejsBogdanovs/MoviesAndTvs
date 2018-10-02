package lv.st.sbogdano.cinema.internal.util

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter
import android.view.View

fun View.fade(show: Boolean) {

    ViewCompat.animate(this).cancel()

    if (show) {
        if (!isShown) {
            visibility = View.VISIBLE
            alpha = 0f
            ViewCompat.animate(this).alpha(1f).start()
        }
    } else {
        if (isShown) {
            alpha = 1f
            ViewCompat.animate(this)
                    .alpha(0f)
                    .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                        override fun onAnimationEnd(view: View?) {
                            if (view != null) {
                                ViewCompat.animate(view).setListener(null)
                            }
                            view?.visibility = View.INVISIBLE
                        }
                    }).start()
        }
    }
}