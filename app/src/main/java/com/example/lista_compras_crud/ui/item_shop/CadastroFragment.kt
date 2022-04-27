package com.example.lista_compras_crud.ui.item_shop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lista_compras_crud.R
import com.example.lista_compras_crud.data.db.AppDatabase
import com.example.lista_compras_crud.data.db.dao.ItemShopDAO
import com.example.lista_compras_crud.extension.hideKeyboard
import com.example.lista_compras_crud.repository.DatabaseDataSource
import com.example.lista_compras_crud.repository.ItemShopRepository
import com.example.lista_compras_crud.ui.item_shop_list.ItemShopListFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : Fragment(R.layout.cadastro_fragment) {

    private val viewModel: ItemShopViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val itemShopDAO: ItemShopDAO =
                    AppDatabase.getInstance(requireContext()).itemShopDAO

                val repository: ItemShopRepository = DatabaseDataSource(itemShopDAO)
                return ItemShopViewModel(repository) as T
            }
        }
    }

    private val args: CadastroFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.shopItem?.let { itemShop ->
            button_cadastro.text = getString(R.string.button_cadastro_update)
            input_item_name.setText(itemShop.name)
            input_item_quant.setText(itemShop.quantity)
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.itemShopStateEventData.observe(viewLifecycleOwner) { itemShopState ->
            when (itemShopState) {
                is ItemShopViewModel.ItemShopState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }

                is ItemShopViewModel.ItemShopState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_item_name.text?.clear()
        input_item_quant.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_cadastro.setOnClickListener {
            val name = input_item_name.text.toString()
            val quantity = input_item_name.text.toString()

            viewModel.addOrUpdateItemShop(name, quantity, args.shopItem?.id ?: 0)
        }
    }
}