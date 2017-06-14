package chuprin.serg.kotlin_github.main.login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import chuprin.serg.kotlin_github.main.login.CustomTabsHelper
import chuprin.serg.kotlin_github.main.login.LoginModule
import chuprin.serg.kotlin_github.main.login.model.LoginInteractor
import chuprin.serg.kotlin_github.main.login.model.entity.NoAuthError
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject lateinit var interactor: LoginInteractor

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_login)
        KotApplication.component.loginComponent(LoginModule()).inject(this)
        handleIntent(intent)
    }

    fun showProgress(visible: Boolean) = progress.visibility(visible)

    fun handleIntent(intent: Intent?) {
        interactor.retrieveToken(intent)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress(true) }
                .doOnTerminate { showProgress(false) }
                .subscribe({ finish() }, { handleError(it) })
    }

    private fun handleError(throwable: Throwable?) {
        if (throwable is NoAuthError) {
            CustomTabsHelper.openInCustomTabOrBrowser(this, interactor.buildUrl())
            finish()
        }
    }
}