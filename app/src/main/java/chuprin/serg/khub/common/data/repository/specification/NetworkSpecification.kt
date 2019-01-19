package chuprin.serg.khub.common.data.repository.specification

import io.reactivex.Observable

interface NetworkSpecification<in API, MODEL> : Specification {

    fun toNetResults(api: API): Observable<MODEL>

}