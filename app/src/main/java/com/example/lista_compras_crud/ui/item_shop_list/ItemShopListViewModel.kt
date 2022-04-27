package com.example.lista_compras_crud.ui.item_shop_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity
import com.example.lista_compras_crud.repository.ItemShopRepository
import kotlinx.coroutines.launch

class ItemShopListViewModel(
    private val repository: ItemShopRepository
) : ViewModel() {

    private val _allItemShopEvent = MutableLiveData<List<ItemShopEntity>>()
    val allItemShopEvent: LiveData<List<ItemShopEntity>>
        get() = _allItemShopEvent

    fun getItemsShop() = viewModelScope.launch {
        _allItemShopEvent.postValue(repository.getAllItemsShop())
    }
}