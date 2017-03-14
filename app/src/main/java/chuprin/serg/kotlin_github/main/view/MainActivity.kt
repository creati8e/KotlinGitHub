package chuprin.serg.kotlin_github.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chuprin.serg.kotlin_github.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val pagerAdapter: MainPagerAdapter = MainPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
        setSupportActionBar(toolbar)
    }
}
