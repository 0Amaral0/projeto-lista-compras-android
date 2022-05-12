package com.example.lista_compras_crud.ui.item_shop_list

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity
import com.example.lista_compras_crud.repository.ItemShopRepository
import com.example.lista_compras_crud.ui.item_shop.ItemShopViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class ItemShopListViewModel(
    private val repository: ItemShopRepository
) : ViewModel() {

    private val _itemShopStateEventData = MutableLiveData<ItemShopViewModel.ItemShopState>()
    val itemShopStateEventData: LiveData<ItemShopViewModel.ItemShopState>
        get() = _itemShopStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    private val _allItemShopEvent = MutableLiveData<List<ItemShopEntity>>()
    val allItemShopEvent: LiveData<List<ItemShopEntity>>
        get() = _allItemShopEvent

    fun getItemsShop() = viewModelScope.launch {
        _allItemShopEvent.postValue(repository.getAllItemsShop())
    }

    fun removeAllItemShop() = viewModelScope.launch  {
        try {
            repository.deleteAllItemsShop()

        } catch (ex: Exception) {
            _messageEventData.value = R.string.item_shop_error_delete_all
            Log.e(ItemShopListViewModel.TAG, ex.toString())
        }
    }

    companion object {
        private val TAG = ItemShopListViewModel::class.java.simpleName
    }
}