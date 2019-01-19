package chuprin.serg.khub.repositories

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.R
import chuprin.serg.khub.common.domain.interactor.AccountInteractor
import chuprin.serg.khub.main.repositories.view.RepositoriesListFragment
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject

class RepositoriesActivity : AppCompatActivity(), View.OnClickListener {

    @Inject lateinit var accountInteractor: AccountInteractor
    private lateinit var repositoriesListFragment: RepositoriesListFragment
    private var hidden = true

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KotApplication.component.inject(this)
        setContentView(R.layout.activity_repositories)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My repositories"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // TODO

//        repositoriesListFragment = instanceOf(
//            RepositoriesListFragment.BUNDLE_USER_LOGIN to accountInteractor.getCurrentAccount().login
//        )
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, repositoriesListFragment)
//            .commit()

        revealLayout.visibility = View.INVISIBLE
        stubBg.setOnClickListener { animateFilter() }
        setListenersForButtons(revealLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_repositoires, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> super.onBackPressed()
            R.id.menu_action_repositories_filter -> animateFilter()
        }
        return super.onOptionsItemSelected(item)
    }

    @TargetApi(21)
    private fun animateFilter() {
        val cx = revealLayout.left + revealLayout.right
        val cy = revealLayout.top

        val endRadius: Float = maxOf(revealLayout.width, revealLayout.height).toFloat()
        if (hidden) {
            with(ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0f, endRadius)) {
                interpolator = AccelerateDecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        revealLayout.visibility = VISIBLE
                        hidden = false
                    }
                })
                start()
            }
            return
        }
        revealLayout.visibility = VISIBLE
        with(ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, endRadius, 0f)) {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    revealLayout.visibility = INVISIBLE
                    hidden = true
                }
            })
            start()
        }
    }

    private fun setListenersForButtons(viewGroup: ViewGroup) {
        (0 until viewGroup.childCount)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                if (it.contentDescription == "menu") {
                    it.setOnClickListener(this)
                } else if (it is ViewGroup) {
                    setListenersForButtons(it)
                }
            }
    }

    override fun onClick(v: View?) {
        repositoriesListFragment.applyFilter(v?.id!!)
        animateFilter()
    }

}