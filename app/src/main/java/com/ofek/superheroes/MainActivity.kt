package com.ofek.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ofek.superheroes.searchscreen.ui.SearchScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationActivity {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragment(SearchScreenFragment.newInstance(), true)
    }

    override fun navigateToFragment(fragment: Fragment, root: Boolean) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .apply {
                if (root.not()) {
                    addToBackStack(fragment.javaClass.name)
                }
            }.commit()
    }
}