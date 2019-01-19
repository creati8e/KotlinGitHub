package chuprin.serg.khub.common.presentation.livedata

import androidx.lifecycle.MutableLiveData

/**
 * @author Sergey Chuprin
 */
class MutableLiveDataDelegate<T>(
    initialState: T? = null
) : BaseLiveDataDelegate<T>(initialState, MutableLiveData())