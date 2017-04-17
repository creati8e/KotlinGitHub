package chuprin.serg.kotlin_github.app.data

import rx.Observable

interface AbsRepository<MODEL> {

    fun getAll(): Observable<List<MODEL>>

    fun get(key: String): Observable<MODEL>
}