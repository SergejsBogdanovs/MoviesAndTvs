package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.PersonLocalDataSource
import lv.st.sbogdano.data.local.model.PersonLocalModel
import lv.st.sbogdano.data.remote.PersonRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.PersonMapper

class PersonRepository(
    private val personLocalDataSource: PersonLocalDataSource,
    private val personRemoteDataSource: PersonRemoteDataSource,
    private val personMapper: PersonMapper
) {

    fun getById(id: Int): Observable<PersonLocalModel> {

        val local = personLocalDataSource.getById(id)

        val remote = personRemoteDataSource.getById(id)
                .map { personMapper.toLocal(it) }
                .doOnNext { personLocalDataSource.insert(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}