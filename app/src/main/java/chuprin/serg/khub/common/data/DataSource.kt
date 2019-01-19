package chuprin.serg.khub.common.data

import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

interface DataSource<MODEL> {

    fun putAll(models: List<MODEL>)

    fun put(model: MODEL)

    fun get(specification: Specification): Observable<MODEL>

    fun getAll(specification: Specification): Observable<List<MODEL>>

    fun delete(model: MODEL)

}