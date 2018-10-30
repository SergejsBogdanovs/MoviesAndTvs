package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.CreditsLocalDataSource
import lv.st.sbogdano.data.local.model.CreditLocalModel
import lv.st.sbogdano.data.remote.CreditsRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.CreditsListMapper

class CreditsRepository(
    private val creditsLocalDataSource: CreditsLocalDataSource,
    private val creditsRemoteDataSource: CreditsRemoteDataSource,
    private val creditsListMapper: CreditsListMapper
) {

    fun getAllById(id: Int): Observable<List<CreditLocalModel>> {

        val local = creditsLocalDataSource.getAllById(id)
                .filter { !it.isEmpty() }

        val remote = creditsRemoteDataSource.getAllById(id)
                .map { it.take(5) }
                .map { creditsListMapper.toLocal(it) }
                .doOnNext { creditsLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}