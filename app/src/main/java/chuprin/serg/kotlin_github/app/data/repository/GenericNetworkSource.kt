package chuprin.serg.kotlin_github.app.data.repository

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import io.reactivex.Observable

@Suppress("UNCHECKED_CAST")
abstract class GenericNetworkSource<MODEL, API>(val api: API) : Source<MODEL> {

    override fun putAll(models: List<MODEL>) = Unit

    override fun put(model: MODEL) = Unit

    override fun delete(model: MODEL) = Unit

    override fun get(specification: Specification): Observable<MODEL> {
        return specification<MODEL>(specification).toNetResults(api)
    }

    override fun getAll(specification: Specification): Observable<List<MODEL>> {
        return specification<List<MODEL>>(specification).toNetResults(api)
    }

    protected fun <MODEL> specification(specification: Specification): NetworkSpecification<API, MODEL> {
        return specification as NetworkSpecification<API, MODEL>
    }
}