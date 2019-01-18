package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.PersonsDao
import lv.st.sbogdano.data.local.model.PersonLocalModel

class PersonLocalDataSource(private val personsDao: PersonsDao) {

    fun getById(id: Int): Observable<PersonLocalModel> = personsDao.getById(id).toObservable()

    fun insert(personLocalModel: PersonLocalModel) = personsDao.insert(personLocalModel)
}