package chuprin.serg.kotlin_github.user

import android.os.Bundle
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import dagger.Module
import dagger.Provides

@Module class UserModule(val bundle: Bundle) {

    @Provides
    @PerView
    fun providePresenter(usersInteractor: UsersInteractor,
                         repositoriesInteractor: RepositoriesInteractor): UserPresenter {
        return UserPresenter(usersInteractor, repositoriesInteractor, bundle.getString("login"))
    }
}
