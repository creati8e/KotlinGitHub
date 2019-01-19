package chuprin.serg.khub.common.presentation.viewmodel

import androidx.lifecycle.ViewModel

/**
 * @author Sergey Chuprin
 *
 * ViewModel which holds Dagger's component and let it survive across configuration changes.
 */
class ComponentViewModel<C>(val component: C) : ViewModel()