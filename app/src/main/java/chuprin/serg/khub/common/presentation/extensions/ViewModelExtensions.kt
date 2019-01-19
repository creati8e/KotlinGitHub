package chuprin.serg.khub.common.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import chuprin.serg.khub.common.presentation.viewmodel.BaseViewModelFactory
import chuprin.serg.khub.common.presentation.viewmodel.ComponentViewModelDelegate

/**
 * @author Sergey Chuprin
 */
inline fun <reified T : ViewModel> Fragment.buildViewModelWithFactory(
    factory: BaseViewModelFactory<T>
): T {
    return ViewModelProviders.of(this, factory).get(T::class.java)
}

inline fun <reified VM : ViewModel> Fragment.viewModel(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
    crossinline provider: () -> VM
): Lazy<VM> {

    return lazy(mode) {
        ViewModelProviders
            .of(this,
                BaseViewModelFactory { provider() })
            .get(VM::class.java)
    }
}

fun <T> componentViewModel(creator: () -> T) =
    ComponentViewModelDelegate(creator)