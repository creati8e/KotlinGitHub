package chuprin.serg.kotlin_github.app.data

import rx.Observable

interface Source<MODEL> {

//    fun get(id: Int): MODEL

    fun getAll(): Observable<List<MODEL>>

    fun putAll(models: List<MODEL>)

    fun get(key: String): Observable<MODEL>

    fun put(model: MODEL)
//
//    fun remove(id: Int)
}