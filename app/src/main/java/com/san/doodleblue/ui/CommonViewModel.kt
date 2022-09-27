package com.san.doodleblue.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.core.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    private val _menuItems = MutableLiveData<List<MenuItem>>(null)
    val menuItems: LiveData<List<MenuItem>> = _menuItems


    init {
        viewModelScope.launch {
            getMenuItems()
        }
    }

    private suspend fun getMenuItems() {
        repository.getAllMenu().collectLatest {
            _menuItems.value = it
        }
    }

    suspend fun insertSampleData() {
        listOf(
            MenuItem(1, "Mutton Biryani", "Mutton Full", 250.0, 0),
            MenuItem(2, "Chicken Fry", "Fried Chicken 6 pcs", 100.0, 0),
            MenuItem(3, "Egg", "Boiled Egg", 15.0, 0),
            MenuItem(4, "Pron Fry", "Fired Pron 10pcs", 200.0, 0),
            MenuItem(5, "Chicken Biryani", "Chicken Full", 150.0, 0),
        ).forEach {
            repository.insertMenuItem(it)
        }
    }

}