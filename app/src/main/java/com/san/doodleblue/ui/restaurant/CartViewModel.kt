package com.san.doodleblue.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.core.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    private val _cartItems = MutableLiveData<List<MenuItem>>(null)
    val cartItems: LiveData<List<MenuItem>> = _cartItems

    val showMore = MutableLiveData((cartItems.value?.size ?: 0) > 2)

    val cartItemVisibleList = Transformations.map(showMore) {
        if (showMore.value == true) cartItems.value?.take(2) else cartItems.value
    }
    val totalAmount = Transformations.map(cartItems) { it?.sumOf { it.totalPrice } }


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