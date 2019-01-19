package chuprin.serg.khub.common.presentation.view.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.reactivex.Completable
import io.reactivex.processors.AsyncProcessor

abstract class BasePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var instantiatedCount = 0
    private var asyncSubject = AsyncProcessor.create<Unit>()

    private val registeredFragments = SparseArray<Fragment>()

    fun observeAdapterInitialized(): Completable {
        return asyncSubject.toObservable().ignoreElements()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        asyncSubject.onNext(Unit)
        ++instantiatedCount
        if (instantiatedCount == count) {
            asyncSubject.onComplete()
        }
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }

}
