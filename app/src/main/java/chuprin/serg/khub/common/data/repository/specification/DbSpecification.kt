package chuprin.serg.khub.common.data.repository.specification

import io.reactivex.Observable

interface DbSpecification<MODEL> : Specification {

    fun toDbResults(): Observable<MODEL>

}