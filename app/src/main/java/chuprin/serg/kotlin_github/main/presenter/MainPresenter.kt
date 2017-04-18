package chuprin.serg.kotlin_github.main.presenter

import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import chuprin.serg.kotlin_github.main.view.MainView
import chuprin.serg.mvpcore.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: CredentialsRepository) : MvpPresenter<MainView>() {

    fun viewResumed() {
        val token = repository.get()
        if (token.isNotEmpty()) {
            view.showUser(token)
        } else {
            view.showUser("Unauthorized")
        }
    }
}