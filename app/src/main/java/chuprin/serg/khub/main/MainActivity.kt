package chuprin.serg.khub.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import chuprin.serg.khub.R
import chuprin.serg.khub.common.presentation.view.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController get() = findNavController(R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (handleBackInFragment().not()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (handleBackInFragment()) true else navController.navigateUp()
    }

    private fun handleBackInFragment(): Boolean {
        val currentFragment = navHostFragment
            ?.childFragmentManager
            ?.fragments
            ?.get(0) as? BaseFragment
            ?: return false

        return currentFragment.handleBack()
    }

}
