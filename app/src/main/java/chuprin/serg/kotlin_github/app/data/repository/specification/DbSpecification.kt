package chuprin.serg.kotlin_github.app.data.repository.specification

import rx.Observable

interface DbSpecification<MODEL> : Specification {

    fun toDbResults(): Observable<MODEL>
}