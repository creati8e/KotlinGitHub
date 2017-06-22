package chuprin.serg.kotlin_github.app.data

import chuprin.serg.kotlin_github.app.data.repository.specification.PaginationSpecification
import io.reactivex.Observable

interface PaginationAbsRepository<MODEL> : AbsRepository<MODEL> {

    fun getPage(specification: PaginationSpecification, offset: Int = -1): Observable<List<MODEL>>

}
