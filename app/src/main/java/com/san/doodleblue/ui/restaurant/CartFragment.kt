package com.san.doodleblue.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.san.doodleblue.R
import com.san.doodleblue.databinding.FragmentCartBinding
import com.san.doodleblue.ui.adapter.MenuItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding

    private val viewModel by viewModels<CartViewModel>()

    private val menuItemAdapter by lazy {
        MenuItemAdapter { lifecycleScope.launch { viewModel.updateMenuItem(it) } }
    }

    private var showMore = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.nsvMenuItems.apply {
            tvMenusTitle.text = getString(R.string.review_ordres)
            rvMenuItems.adapter = menuItemAdapter
        }

        observeCartItems()
    }

    private fun observeCartItems() {
        /*viewModel.cartItems.observe(viewLifecycleOwner) { list ->
            binding.tvTotalAmount.text = list?.sumOf { it.totalPrice }.toString()
            menuItemAdapter.submitList(list)
        }*/
    }

}