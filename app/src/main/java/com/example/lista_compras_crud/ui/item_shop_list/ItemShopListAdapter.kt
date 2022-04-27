package com.example.lista_compras_crud.ui.item_shop_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity
import kotlinx.android.synthetic.main.shop_list_item.view.*

class ItemShopListAdapter(
    private val shop_item: List<ItemShopEntity>
) : RecyclerView.Adapter<ItemShopListAdapter.ItemShopListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemShopListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_list_item, parent, false)

        return ItemShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemShopListViewHolder, position: Int) {
        holder.bindView(shop_item[position])
    }

    override fun getItemCount() = shop_item.size

    class ItemShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewItemShopName: TextView = itemView.txt_item_shop_name
        private val textViewItemShopQuantity: TextView = itemView.txt_item_shop_quantity

        fun bindView(shop_item: ItemShopEntity) {
            textViewItemShopName.text = shop_item.name
            textViewItemShopQuantity.text = shop_item.quantity
        }
    }
}