package chuprin.serg.kotlin_github.app.data

import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable

interface AbsRepository<MODEL> {

    fun get(specification: Specification): Observable<MODEL>

    fun getList(specification: Specification): Observable<List<MODEL>>
}