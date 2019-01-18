package lv.st.sbogdano.cinema.person

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.PersonGetByIdUseCase
import lv.st.sbogdano.domain.model.PersonDomainModel

class PersonDetailViewModel(
        context: Context,
        private val personGetByIdUseCase: PersonGetByIdUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application
) {

    val person = ObservableField<PersonDomainModel>()

    fun loadPersonDetail(id: Int) = addDisposable(getPersonById(id))

    private fun getPersonById(id: Int): Disposable {
        return personGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<PersonDomainModel>() {
                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: PersonDomainModel) {
                        loading.set(false)
                        person.set(t)
                    }

                    override fun onError(e: Throwable) {
                        loading.set(false)
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }

                })
    }

}