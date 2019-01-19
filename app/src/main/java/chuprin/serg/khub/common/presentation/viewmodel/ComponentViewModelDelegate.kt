package chuprin.serg.khub.common.presentation.viewmodel

import androidx.fragment.app.Fragment
import chuprin.serg.khub.common.presentation.extensions.buildViewModelWithFactory
import kotlin.reflect.KProperty

/**
 * @author Sergey Chuprin
 */
class ComponentViewModelDelegate<T>(creator: () -> T) {

    private val factory = BaseViewModelFactory { ComponentViewModel(creator()) }

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Fragment, property: KProperty<*>): ComponentViewModel<T> {
        return thisRef.buildViewModelWithFactory(factory)
    }

}