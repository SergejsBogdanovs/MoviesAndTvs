package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.TvsDao
import lv.st.sbogdano.data.local.model.TvLocalModel

class TvLocalDataSource(private val tvsDao: TvsDao) {

    fun getAll(type: String): Observable<List<TvLocalModel>> = tvsDao.getAll(type).toObservable()

    fun insertAll(tvs: List<TvLocalModel>) = tvsDao.insertAll(*tvs.toTypedArray())

    fun deleteByType(type: String) = tvsDao.deleteByType(type)

    fun getById(id: Int): Observable<TvLocalModel> = tvsDao.getById(id).toObservable()

}