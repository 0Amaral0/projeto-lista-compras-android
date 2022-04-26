package com.example.lista_compras_crud.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lista_compras_crud.data.db.dao.ItemShopDAO
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity

@Database(entities = [ItemShopEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract val itemShopDAO: ItemShopDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}