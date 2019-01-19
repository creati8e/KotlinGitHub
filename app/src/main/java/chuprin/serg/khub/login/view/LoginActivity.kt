package chuprin.serg.khub.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.R
import chuprin.serg.khub.common.presentation.extensions.visibility
import chuprin.serg.khub.login.CustomTabsHelper
import chuprin.serg.khub.login.LoginModule
import chuprin.serg.khub.login.model.LoginInteractor
import chuprin.serg.khub.login.model.entity.NoAuthError
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

    private fun showProgress(visible: Boolean) = progressBar.visibility(visible)

    private fun handleIntent(intent: Intent?) {
        interactor
            .retrieveToken(intent)
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