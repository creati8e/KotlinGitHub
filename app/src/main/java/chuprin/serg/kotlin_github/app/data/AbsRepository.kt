package chuprin.serg.kotlin_github.app.data

import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import io.reactivex.Observable

interface AbsRepository<MODEL> {

    fun get(specification: Specification, cachePolicy: CachePolicy = CachePolicy.BOTH()): Observable<MODEL>

    fun getAll(specification: Specification,
               cachePolicy: CachePolicy = CachePolicy.BOTH()): Observable<List<MODEL>>

    fun put(model: MODEL)

    fun delete(model: MODEL)
}