package chuprin.serg.kotlin_github.app.presentation.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import rx.Completable
import rx.subjects.AsyncSubject


abstract class BasePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    val registeredFragments = SparseArray<Fragment>()
    var mAsyncSubject: AsyncSubject<Void> = AsyncSubject.create()
    var mInstantiatedCount = 0

    fun observeAdapterInitialized(): Completable {
        return mAsyncSubject.asObservable().toCompletable()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        mAsyncSubject.onNext(null)
        ++mInstantiatedCount
        if (mInstantiatedCount == count) {
            mAsyncSubject.onCompleted()
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
