package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.CreditsDao
import lv.st.sbogdano.data.local.model.CreditLocalModel

class CreditsLocalDataSource(private val creditsDao: CreditsDao) {

    fun getAllById(id: Int): Observable<List<CreditLocalModel>> = creditsDao.getAllById(id).toObservable()

    fun insertAll(credits: List<CreditLocalModel>) = creditsDao.insertAll(*credits.toTypedArray())
}