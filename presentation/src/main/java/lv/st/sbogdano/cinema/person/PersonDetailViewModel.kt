package lv.st.sbogdano.cinema.person

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.MovieCreditsGetByPersonIdUseCase
import lv.st.sbogdano.domain.interactor.PersonGetByIdUseCase
import lv.st.sbogdano.domain.model.MovieCreditDomainModel
import lv.st.sbogdano.domain.model.PersonDomainModel

class PersonDetailViewModel(
        context: Context,
        private val personGetByIdUseCase: PersonGetByIdUseCase,
        private val movieCreditsGetByPersonIdUseCase: MovieCreditsGetByPersonIdUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application
) {

    val person = ObservableField<PersonDomainModel>()
    val movieCredits = ObservableArrayList<MovieCreditDomainModel>()

    fun loadPersonDetail(id: Int) = addDisposable(getPersonById(id))
    fun loadMovieCredits(person: Int) = addDisposable(getMovieCreditsByPersonId(person))

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

    private fun getMovieCreditsByPersonId(person: Int): Disposable {
        return movieCreditsGetByPersonIdUseCase.execute(person)
                .subscribeWith(object : DisposableObserver<List<MovieCreditDomainModel>>() {
                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<MovieCreditDomainModel>) {
                        loading.set(false)
                        movieCredits.clear()
                        movieCredits.addAll(t)
                        empty.set(t.isEmpty())
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