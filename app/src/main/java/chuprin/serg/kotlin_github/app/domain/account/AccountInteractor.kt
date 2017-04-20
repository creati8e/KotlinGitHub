package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import javax.inject.Inject

class AccountInteractor
@Inject constructor(private val credentialsRepository: CredentialsRepository) {

    fun getAllAccounts() = credentialsRepository.getList(GetAllAccountSpecification())

    fun getCurrentAccount() = credentialsRepository.get(GetActiveAccountSpecification())

    fun setCurrentAccount(account: GithubAccount) = credentialsRepository.put(account)
}