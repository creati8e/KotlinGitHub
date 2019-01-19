package chuprin.serg.khub.common.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Sergey Chuprin
 */
abstract class BaseViewModel : ViewModel() {

    private val onClearedDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() = onClearedDisposable.clear()

    fun Disposable.untilCleared(): Disposable = this.apply { onClearedDisposable.add(this) }

}