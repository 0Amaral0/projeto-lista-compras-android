package com.example.lista_compras_crud.repository

import androidx.lifecycle.LiveData
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity


interface ItemShopRepository {

    suspend fun insertItemShop(name: String, quantity: String): Long

    suspend fun updateItemShop(id: Long, name: String, quantity: String)

    suspend fun deleteItemShop(id: Long)

    suspend fun deleteAllItemsShop()

    fun getAllItemsShop(): LiveData<List<ItemShopEntity>>
}