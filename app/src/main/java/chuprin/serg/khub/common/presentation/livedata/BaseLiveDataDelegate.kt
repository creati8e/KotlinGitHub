package chuprin.serg.khub.common.presentation.livedata

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author Sergey Chuprin
 */
abstract class BaseLiveDataDelegate<T>(
    initialState: T?,
    protected val liveData: MutableLiveData<T>
) {

    init {
        liveData.value = initialState
    }

    val asLiveData: LiveData<T> get() = liveData

    var value: T?
        get() = liveData.value
        set(value) = internalSetValue(value)

    fun setter(value: T?) = internalSetValue(value)

    private fun internalSetValue(value: T?) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            liveData.value = value
        } else {
            liveData.postValue(value)
        }
    }

}