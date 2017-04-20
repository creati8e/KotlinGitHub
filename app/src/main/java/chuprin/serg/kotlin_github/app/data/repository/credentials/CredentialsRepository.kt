package chuprin.serg.kotlin_github.app.data.repository.credentials

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import javax.inject.Inject

class CredentialsRepository
@Inject constructor(private val dbSource: Source<GithubAccount>) : AbsRepository<GithubAccount> {

    override fun get(specification: Specification, cachePolicy: CachePolicy) = dbSource.get(specification)

    override fun getList(specification: Specification) = dbSource.getList(specification)

    override fun put(model: GithubAccount) = dbSource.put(model)
}