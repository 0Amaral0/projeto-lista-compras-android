package com.example.lista_compras_crud.repository

import androidx.lifecycle.LiveData
import com.example.lista_compras_crud.data.db.dao.ItemShopDAO
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity

class DatabaseDataSource(
    private val itemDAO: ItemShopDAO
) : ItemShopRepository {

        override suspend fun insertItemShop(name: String, quantity: String): Long {
            val itemShop = ItemShopEntity(
                name = name,
                quantity = quantity
            )

            return itemDAO.insert(itemShop)
        }

        override suspend fun updateItemShop(id: Long, name: String, quantity: String) {
            val itemShop = ItemShopEntity(
                id = id,
                name = name,
                quantity = quantity
            )

            itemDAO.update(itemShop)
        }

        override suspend fun deleteItemShop(id: Long) {
            itemDAO.delete(id)
        }

        override suspend fun deleteAllItemsShop() {
            itemDAO.deleteAll()
        }

        override fun getAllItemsShop(): LiveData<List<ItemShopEntity>> {
            return itemDAO.getAll()
        }

}