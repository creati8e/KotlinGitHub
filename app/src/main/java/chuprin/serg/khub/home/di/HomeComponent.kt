package chuprin.serg.khub.home.di

import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.home.model.HomeViewModel
import dagger.Subcomponent

@PerView
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    companion object {
        fun get(): HomeComponent {
            return KotApplication.component.homeComponent(HomeModule())
        }
    }

    val viewModel: HomeViewModel

}