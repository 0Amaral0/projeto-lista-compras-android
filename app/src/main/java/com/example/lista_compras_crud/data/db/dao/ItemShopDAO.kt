package com.example.lista_compras_crud.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity

@Dao
interface ItemShopDAO {
    @Insert
    suspend fun insert(shop_item: ItemShopEntity): Long

    @Update
    suspend fun update(shop_item: ItemShopEntity)

    @Query("DELETE FROM shop_item WHERE id = :id")
    suspend fun delete (id: Long)

    @Query("DELETE FROM shop_item")
    suspend fun deleteAll()

    @Query("SELECT * FROM shop_item")
    fun getAll() : LiveData<List<ItemShopEntity>>
}