package chuprin.serg.khub.common.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


/**
 * This function creates a [LiveData] of a [Pair] of the two types provided.
 * The resulting LiveData is updated whenever either input LiveData updates
 * and both LiveData have updated to a non-null value at least once before.
 *
 * If the zip of A and B is C, and A and B are updated in this pattern: `AABA`,
 * C would be updated twice (once with the second A value and first B value,
 * and once with the third A value and first B value).
 *
 * @param b the second LiveData
 * @author Mitchell Skaggs
 */
fun <A, B> LiveData<A>.zipWith(b: LiveData<B>): LiveData<Pair<A, B>> {
    return MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(this@zipWith) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }
}

fun <X, Y> LiveData<X>.map(mapFunction: (X) -> Y): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    result.addSource(this) { x -> result.value = mapFunction(x) }
    return result
}