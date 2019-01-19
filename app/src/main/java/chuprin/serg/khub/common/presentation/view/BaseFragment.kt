package chuprin.serg.khub.common.presentation.view

import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import chuprin.serg.khub.common.presentation.livedata.BaseLiveDataDelegate
import crif.contact.core.api.presentation.view.MenuConfig
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Sergey Chuprin
 */
abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected var menu: Menu? = null

    protected open val menuConfig: MenuConfig? = null

    protected val appCompatActivity: AppCompatActivity
        get() = requireActivity() as AppCompatActivity

    protected val navController get() = findNavController()

    private val onStopDisposable = CompositeDisposable()
    private val onDestroyViewDisposable = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (menuConfig != null) setHasOptionsMenu(true)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        onStopDisposable.clear()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyViewDisposable.clear()
    }

    // region Menu.

    @CallSuper
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menuConfig?.menuRes?.let { menuRes -> inflater.inflate(menuRes, menu) }
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuConfig?.onMenuItemClick?.invoke(item)
        return super.onOptionsItemSelected(item)
    }

    @CallSuper
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menuConfig?.onPrepareMenu?.invoke(menu)
        this.menu = menu
    }

    open fun handleBack(): Boolean = false


    inline fun <T> LiveData<T>.observe(crossinline observer: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { if (it != null) observer(it) })
    }

    inline fun <T> BaseLiveDataDelegate<T>.observe(crossinline observer: (T) -> Unit) {
        asLiveData.observe(observer)
    }

    //endregion

    protected fun Disposable.untilStop() = onStopDisposable.add(this)
    protected fun Disposable.untilDestroyView() = onDestroyViewDisposable.add(this)

}