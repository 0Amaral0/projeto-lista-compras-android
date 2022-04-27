package com.example.lista_compras_crud.ui.item_shop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.repository.ItemShopRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ItemShopViewModel(
    private val repository: ItemShopRepository
) : ViewModel() {

    private val _itemShopStateEventData = MutableLiveData<ItemShopState>()
    val itemShopStateEventData: LiveData<ItemShopState>
        get() = _itemShopStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateItemShop(name: String, quantity: String, id: Long = 0) {
        if (id > 0) {
            updatetItemShop(id, name, quantity)
        } else {
            insertItemShop(name, quantity)
        }
    }

    private fun updatetItemShop(id: Long, name: String, quantity: String) = viewModelScope.launch {
        try {
            repository.updateItemShop(id, name, quantity)

            _itemShopStateEventData.value = ItemShopState.Updated
            _messageEventData.value = R.string.item_shop_success_update

        } catch (ex: Exception) {
            _messageEventData.value = R.string.item_shop_error_insert
            Log.e(TAG, ex.toString())
        }
    }

    private fun insertItemShop(name: String, quantity: String) = viewModelScope.launch {
        try {
            val id = repository.insertItemShop(name, quantity)
            if (id > 0) {
                _itemShopStateEventData.value = ItemShopState.Inserted
                _messageEventData.value = R.string.item_shop_success_insert
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.item_shop_error_insert
            Log.e(TAG, ex.toString())
        }
    }

    sealed class ItemShopState {
        object Inserted : ItemShopState()
        object Updated : ItemShopState()
    }

    companion object {
        private val TAG = ItemShopViewModel::class.java.simpleName
    }
}