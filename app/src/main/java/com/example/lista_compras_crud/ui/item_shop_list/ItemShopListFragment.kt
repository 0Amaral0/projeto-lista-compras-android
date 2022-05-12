package com.example.lista_compras_crud.ui.item_shop_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.data.db.AppDatabase
import com.example.lista_compras_crud.data.db.dao.ItemShopDAO
import com.example.lista_compras_crud.data.db.entity.ItemShopEntity
import com.example.lista_compras_crud.extension.navigateWithAnimations
import com.example.lista_compras_crud.repository.DatabaseDataSource
import com.example.lista_compras_crud.repository.ItemShopRepository
import com.example.lista_compras_crud.ui.item_shop.ItemShopViewModel
import kotlinx.android.synthetic.main.item_shop_list_fragment.*

class ItemShopListFragment : Fragment(R.layout.item_shop_list_fragment) {

    private lateinit var builder : AlertDialog.Builder

    private val viewModel: ItemShopListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val itemShopDAO: ItemShopDAO =
                    AppDatabase.getInstance(requireContext()).itemShopDAO

                val repository: ItemShopRepository = DatabaseDataSource(itemShopDAO)
                return ItemShopListViewModel(repository) as T

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allItemShopEvent.observe(viewLifecycleOwner) { allItemsShop ->
            val itemShopListAdapter = ItemShopListAdapter(allItemsShop).apply {
                onItemClick = { itemShop ->
                    val directions = ItemShopListFragmentDirections
                        .actionItemShopListFragmentToCadastroFragment(itemShop)
                    findNavController().navigateWithAnimations(directions)
                }
            }

            with (recycler_items_shop) {
                setHasFixedSize(true)
                adapter = itemShopListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItemsShop()
    }

    private fun configureViewListeners() {

        builder = AlertDialog.Builder(requireActivity())

        fabDeleteAllItemShop.setOnClickListener {
            builder.setTitle("Limpar o Carrinho")
                .setMessage("Deseja deletar todos os itens da sua lista?")
                .setCancelable(true)

                .setPositiveButton("Sim"){dialogInterface,it ->
                    viewModel.removeAllItemShop()
                    viewModel.getItemsShop()
                }

                .setNegativeButton("Não"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .show()
        }

        fabAddItemShop.setOnClickListener {
            findNavController().navigateWithAnimations(
                R.id.action_itemShopListFragment_to_cadastroFragment
            )
        }
    }
}