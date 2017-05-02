package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import javax.inject.Inject

class AccountInteractor
@Inject constructor(private val credentialsRepository: AbsRepository<GithubAccount>) {

    fun getAllAccounts() = credentialsRepository.getList(GetAllAccountsSpecification())

    fun getCurrentAccount(): GithubAccount = credentialsRepository.get(GetActiveAccountSpecification()).blockingFirst()

    fun setCurrentAccount(account: GithubAccount) = credentialsRepository.put(account)

    fun logout() = credentialsRepository.delete(getCurrentAccount())
}