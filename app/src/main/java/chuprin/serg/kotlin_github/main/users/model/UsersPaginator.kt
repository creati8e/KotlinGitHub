package chuprin.serg.kotlin_github.main.users.model

import chuprin.serg.kotlin_github.app.data.PaginationAbsRepository
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.pagintation.Paginator
import chuprin.serg.kotlin_github.app.domain.users.AllUsersSpecification
import io.reactivex.Observable
import javax.inject.Inject

class UsersPaginator @Inject constructor(val repository: PaginationAbsRepository<GithubUserEntity>)
    : Paginator<GithubUserEntity>() {

    private var lastUserId: Int = -1

    override fun nextPage(offset: Int): Observable<List<GithubUserEntity>> {
        val actualOffset = if (lastUserId == -1) offset else lastUserId
        return repository.getPage(AllUsersSpecification(), actualOffset)
                .doOnNext { if (it.isNotEmpty()) lastUserId = it.last().id }
    }

}