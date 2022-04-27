package com.example.lista_compras_crud.ui.item_shop_list

import androidx.lifecycle.ViewModel
import com.example.lista_compras_crud.repository.ItemShopRepository

class ItemShopListViewModel(
    private val repository: ItemShopRepository
) : ViewModel() {

    val allItemShopEvent = repository.getAllItemsShop()

}