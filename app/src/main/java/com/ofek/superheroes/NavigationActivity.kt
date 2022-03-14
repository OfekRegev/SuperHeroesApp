package com.ofek.superheroes

import androidx.fragment.app.Fragment

interface NavigationActivity {

    fun navigateToFragment(fragment: Fragment, root: Boolean)
}