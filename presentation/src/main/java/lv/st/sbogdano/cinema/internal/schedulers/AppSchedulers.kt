package lv.st.sbogdano.cinema.internal.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import lv.st.sbogdano.domain.Schedulers

class AppSchedulers : Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler
        get() = AndroidSchedulers.mainThread()
}