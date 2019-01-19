package chuprin.serg.khub.common.presentation.livedata

/**
 * @author Sergey Chuprin
 */
class SingeEventLiveDataDelegate<T>(
    initialState: T? = null
) : BaseLiveDataDelegate<T>(initialState, SingleEventLiveData())