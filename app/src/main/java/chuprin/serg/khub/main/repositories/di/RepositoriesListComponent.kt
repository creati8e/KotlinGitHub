package chuprin.serg.khub.main.repositories.di

import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.main.repositories.model.RepositoriesListViewModel
import dagger.Subcomponent

/**
 * @author Sergey Chuprin
 */
@PerView
@Subcomponent(modules = [RepositoriesListModule::class])
interface RepositoriesListComponent {

    companion object {
        fun get(login: String): RepositoriesListComponent {
            return KotApplication.component.repositoriesComponent(RepositoriesListModule(login))
        }
    }

    val viewModel: RepositoriesListViewModel

}