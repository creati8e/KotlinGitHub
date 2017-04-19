package chuprin.serg.kotlin_github.app.data.repository.specification

import rx.Observable

interface NetworkSpecification<in API, MODEL> : Specification {

    fun toNetResults(api: API): Observable<MODEL>
}