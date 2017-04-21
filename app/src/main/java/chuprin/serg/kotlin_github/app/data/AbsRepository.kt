package chuprin.serg.kotlin_github.app.data

import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable

interface AbsRepository<MODEL> {

    fun get(specification: Specification, cachePolicy: CachePolicy = CachePolicy.BOTH()): Observable<MODEL>

    fun getList(specification: Specification): Observable<List<MODEL>>

    fun put(model: MODEL)

    fun delete(model: MODEL)
}