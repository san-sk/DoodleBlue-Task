package com.san.doodleblue.ui.restaurant

import androidx.lifecycle.*
import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.core.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    private val _menuItems = MutableLiveData<List<MenuItem>>(null)
    val menuItems: LiveData<List<MenuItem>> = _menuItems

    val cartCount = Transformations.map(menuItems) { it?.sumOf { it.count } }


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

    suspend fun insertMenuItem(menuItem: MenuItem) {
        repository.insertMenuItem(menuItem)
    }

    suspend fun updateMenuItem(menuItem: MenuItem) {
        repository.updateMenuItem(menuItem)
    }
}