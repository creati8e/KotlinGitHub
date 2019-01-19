package chuprin.serg.khub.common.data

import chuprin.serg.khub.common.data.repository.specification.PaginationSpecification
import io.reactivex.Observable

interface PaginationRepository<MODEL> : Repository<MODEL> {

    fun getPage(specification: PaginationSpecification, offset: Int = -1): Observable<List<MODEL>>

}
