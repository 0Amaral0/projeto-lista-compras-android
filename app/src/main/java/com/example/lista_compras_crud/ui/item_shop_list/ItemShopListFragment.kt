package com.example.lista_compras_crud.ui.item_shop_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity
import kotlinx.android.synthetic.main.item_shop_list_fragment.*

class ItemShopListFragment : Fragment(R.layout.item_shop_list_fragment) {
    private lateinit var viewModel: ItemShopListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemShopListAdapter = ItemShopListAdapter(
            listOf(
                ItemShopEntity(1, "Ovo", "Uma dúzia"),
                ItemShopEntity(2, "Picanha", "3Kg"),
                ItemShopEntity(3, "Azeite de Oliva", "3 unidades")
            )
        )

        recycler_items_shop.run {
            setHasFixedSize(true)
            adapter = itemShopListAdapter
        }
    }
}