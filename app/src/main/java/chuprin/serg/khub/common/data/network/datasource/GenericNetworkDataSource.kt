package chuprin.serg.khub.common.data.network.datasource

import chuprin.serg.khub.common.data.DataSource
import chuprin.serg.khub.common.data.repository.specification.NetworkSpecification
import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

@Suppress("UNCHECKED_CAST")
abstract class GenericNetworkDataSource<MODEL, API>(val api: API) : DataSource<MODEL> {

    override fun putAll(models: List<MODEL>) = Unit

    override fun put(model: MODEL) = Unit

    override fun delete(model: MODEL) = Unit

    override fun get(specification: Specification): Observable<MODEL> {
        return specification<MODEL>(specification).toNetResults(api)
    }

    override fun getAll(specification: Specification): Observable<List<MODEL>> {
        return specification<List<MODEL>>(specification).toNetResults(api)
    }

    private fun <MODEL> specification(specification: Specification): NetworkSpecification<API, MODEL> {
        return specification as NetworkSpecification<API, MODEL>
    }

}