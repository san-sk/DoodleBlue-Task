package com.san.doodleblue.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.core.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    private val _cartItems = MutableStateFlow<List<MenuItem>>(listOf())
    val cartItems = _cartItems.asStateFlow()

    val totalAmount = MutableStateFlow(0.0)


    init {
        viewModelScope.launch {
            getCartItems()
        }
    }


    private suspend fun getCartItems() {
        repository.getCartList().collectLatest { list ->
            _cartItems.value = list
            totalAmount.value = list.sumOf { it.totalPrice }
        }
    }


    suspend fun updateMenuItem(menuItem: MenuItem) {
        repository.updateMenuItem(menuItem)
    }


}