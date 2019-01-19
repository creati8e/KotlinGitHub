package chuprin.serg.khub.main.repositories.di

import chuprin.serg.khub.common.domain.interactor.RepositoryInteractor
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.main.repositories.model.RepositoriesListViewModel
import dagger.Module
import dagger.Provides

/**
 * @author Sergey Chuprin
 */
@Module
class RepositoriesListModule(private val login: String) {

    @Provides
    @PerView
    fun provideRepositoriesListViewModel(
        interactor: RepositoryInteractor
    ): RepositoriesListViewModel {
        return RepositoriesListViewModel(login, interactor)
    }

}