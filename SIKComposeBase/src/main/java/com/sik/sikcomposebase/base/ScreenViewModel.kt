package com.sik.sikcomposebase.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ScreenViewModel : BaseViewModel() {
    private val _screens = MutableLiveData<List<Screen>>(emptyList())
    val screens: LiveData<List<Screen>> = _screens

    fun addScreen(screen: Screen) {
        _screens.value = _screens.value?.plus(screen)
    }

    fun removeScreen(route: String) {
        _screens.value = _screens.value?.filterNot { it.route == route }
    }
}
