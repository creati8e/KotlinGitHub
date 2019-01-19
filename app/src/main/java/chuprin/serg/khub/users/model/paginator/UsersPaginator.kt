package chuprin.serg.khub.users.model.paginator

import chuprin.serg.khub.common.data.database.specification.user.AllUsersSpecification
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import chuprin.serg.khub.common.domain.pagintation.Paginator
import io.reactivex.Observable

class UsersPaginator(
    val repository: GithubUserRepository
) : Paginator<GithubUserEntity>() {

    private var lastUserId: Int = -1

    override fun nextPage(offset: Int): Observable<List<GithubUserEntity>> {
        val actualOffset = if (lastUserId == -1) offset else lastUserId
        return repository.getPage(AllUsersSpecification(), actualOffset)
            .doOnNext { if (it.isNotEmpty()) lastUserId = it.last().id }
    }

}