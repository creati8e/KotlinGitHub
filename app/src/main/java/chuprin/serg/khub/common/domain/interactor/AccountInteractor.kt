package chuprin.serg.khub.common.domain.interactor

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.database.specification.account.GetActiveAccountSpecification
import chuprin.serg.khub.common.data.database.specification.account.GetAllAccountsSpecification
import chuprin.serg.khub.common.data.repository.CredentialsRepository

class AccountInteractor(
    private val credentialsRepository: CredentialsRepository
) {

    fun getAllAccounts() = credentialsRepository.getAll(GetAllAccountsSpecification())

    fun getCurrentAccount(): GithubAccountDbDto {
        return credentialsRepository.get(GetActiveAccountSpecification()).blockingFirst()
    }

    fun setCurrentAccount(account: GithubAccountDbDto) = credentialsRepository.put(account)

    fun logout() = credentialsRepository.delete(getCurrentAccount())

}