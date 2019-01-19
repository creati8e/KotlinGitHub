package chuprin.serg.khub.home.di

import chuprin.serg.khub.common.domain.interactor.UserInteractor
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.home.model.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @PerView
    fun provideMainViewModel(interactor: UserInteractor): HomeViewModel {
        return HomeViewModel(interactor)
    }

}