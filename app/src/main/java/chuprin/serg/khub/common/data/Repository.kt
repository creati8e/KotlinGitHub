package chuprin.serg.khub.common.data

import chuprin.serg.khub.common.data.repository.CachePolicy
import chuprin.serg.khub.common.data.repository.CachePolicy.BOTH
import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

interface Repository<MODEL> {

    fun get(
        specification: Specification,
        cachePolicy: CachePolicy = BOTH
    ): Observable<MODEL>

    fun getAll(
        specification: Specification,
        cachePolicy: CachePolicy = BOTH
    ): Observable<List<MODEL>>

    fun put(model: MODEL)

    fun delete(model: MODEL)

}