package chuprin.serg.khub.main.repositories.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import chuprin.serg.khub.R
import chuprin.serg.khub.common.presentation.extensions.args
import chuprin.serg.khub.common.presentation.extensions.componentViewModel
import chuprin.serg.khub.common.presentation.extensions.viewModel
import chuprin.serg.khub.common.presentation.view.BaseFragment
import chuprin.serg.khub.main.repositories.di.RepositoriesListComponent
import chuprin.serg.khub.main.repositories.view.adapter.RepositoryRenderer
import chuprin.serg.khub.main.repositories.view.argument.RepositoriesListArguments
import kotlinx.android.synthetic.main.fragment_list.*
import serg.chuprin.adapter.MultiViewAdapter

class RepositoriesListFragment : BaseFragment() {

    override val layoutResId: Int = R.layout.fragment_list

    private val viewModel by viewModel { componentViewModel.component.viewModel }
    private val fragmentArgs by args<RepositoriesListArguments>()
    private val componentViewModel by componentViewModel {
        RepositoriesListComponent.get(fragmentArgs.login)
    }

    private val adapter = MultiViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.registerRenderer(RepositoryRenderer())
        adapter.clickListener = { model, _, _ -> onAdapterClick(model) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listLiveData.observe(adapter::setItems)
    }

    fun applyFilter(id: Int) = viewModel.applyFilter(id)

    private fun onAdapterClick(model: Any) {
        // TODO
    }

}