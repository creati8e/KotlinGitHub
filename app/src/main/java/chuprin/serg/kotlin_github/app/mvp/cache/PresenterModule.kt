package chuprin.serg.kotlin_github.app.mvp.cache

import android.os.Bundle
import dagger.Module

@Module
open class PresenterModule(protected val bundle: Bundle?,
                           protected val cache: PresenterCache = PresenterCache)
