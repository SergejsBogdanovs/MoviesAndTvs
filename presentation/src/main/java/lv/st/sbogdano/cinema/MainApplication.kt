package lv.st.sbogdano.cinema

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import lv.st.sbogdano.cinema.internal.injection.DaggerApplication
import timber.log.Timber
import java.io.IOException
import java.net.SocketException

class MainApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        Timber.plant(Timber.DebugTree())

        setupReactiveX()
    }

    private fun setupReactiveX() {
        // Error handling in RxJava
        // https://github.com/ReactiveX/RxJava/wiki/What%27s-different-in-2.0#error-handling
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                Timber.w(e.cause, "Undeliverable exception received, not sure what to do")
                return@setErrorHandler
            }
            if (e is IOException || e is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (e is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (e is NullPointerException || e is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
        }
    }
}