package lv.st.sbogdano.cinema.internal.util.databinding

import android.databinding.BindingAdapter
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import lv.st.sbogdano.cinema.BuildConfig
import lv.st.sbogdano.cinema.internal.util.fade

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("fadeView")
    fun fadeView(view: View, show: Boolean) {
        view.fade(show)
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, text: String?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        text?.let {
            val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("loadUrl", "isCircularImageView", requireAll = false)
    fun loadUrl(imageView: ImageView, url: String?, isCircularImageView: Boolean = false) {
        val options = if (isCircularImageView) {
            RequestOptions.circleCropTransform()
        } else {
            RequestOptions.noTransformation()
        }
        url?.let {
            Glide.with(imageView.context)
                    .load(BuildConfig.POSTER_BASE_URL + it)
                    .apply(options)
                    .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("showVideo")
    fun playVideo(youTubePlayerView: YouTubePlayerView, key: String?) {
        key?.let {
            youTubePlayerView.initialize({ youTubePlayer ->
                youTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady() {
                        youTubePlayer.cueVideo(key, 0f)
                    }

                })
            }, true)
        }
    }
}
