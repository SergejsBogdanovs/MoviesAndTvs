package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.TvDao
import lv.st.sbogdano.data.local.model.TvLocalModel

class TvLocalDataSource(private val tvDao: TvDao) {

    fun getAll(type: String): Observable<List<TvLocalModel>> = tvDao.getAll(type).toObservable()

    fun insertAll(tvs: List<TvLocalModel>) = tvDao.insertAll(*tvs.toTypedArray())

    fun deleteByType(type: String) = tvDao.deleteByType(type)
}