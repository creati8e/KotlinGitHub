package chuprin.serg.kotlin_github.repositories

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import chuprin.serg.kotlin_github.app.presentation.view.utils.instanceOf
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListFragment
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject

class RepositoriesActivity : AppCompatActivity() {

    @Inject lateinit var credentialsRepository: CredentialsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KotApplication.component.inject(this)
        setContentView(R.layout.activity_repositories)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My repositories"

        val fragment = instanceOf<RepositoriesListFragment>(
                RepositoriesListFragment.BUNDLE_USER_LOGIN to credentialsRepository.getLogin())

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
    }
}