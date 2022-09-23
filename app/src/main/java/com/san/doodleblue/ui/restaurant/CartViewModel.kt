package com.san.doodleblue.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.doodleblue.data.entity.MenuItem
import com.san.doodleblue.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    private val _cartItems = MutableLiveData<List<MenuItem>>(null)
    val cartItems: LiveData<List<MenuItem>> = _cartItems


    init {
        viewModelScope.launch {
            getCartItems()
        }
    }


    private suspend fun getCartItems() {
        repository.getCartList().collectLatest {
            _cartItems.value = it
        }
    }

    suspend fun updateMenuItem(menuItem: MenuItem) {
        repository.updateMenuItem(menuItem)
    }


}