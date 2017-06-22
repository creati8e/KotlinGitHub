package chuprin.serg.kotlin_github.app.data.repository

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import io.reactivex.Observable
import io.realm.RealmObject

@Suppress("UNCHECKED_CAST")
abstract class GenericDbSource<MODEL : RealmObject> : Source<MODEL> {

    override fun putAll(models: List<MODEL>) = models.saveAll()

    override fun put(model: MODEL) = model.save()

    override fun get(specification: Specification): Observable<MODEL> {
        return specification<MODEL>(specification).toDbResults()
    }

    override fun getAll(specification: Specification): Observable<List<MODEL>> {
        return specification<List<MODEL>>(specification).toDbResults()
    }

    protected fun <MODEL> specification(specification: Specification): DbSpecification<MODEL> {
        return specification as DbSpecification<MODEL>
    }
}