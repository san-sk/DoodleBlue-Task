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
import com.san.doodleblue.databinding.FragmentHomeBinding
import com.san.doodleblue.ui.adapter.MenuItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private val menuItemAdapter by lazy {
        MenuItemAdapter { lifecycleScope.launch { viewModel.updateMenuItem(it) } }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nsvMenuItems.rvMenuItems.adapter = menuItemAdapter
        observeMenuItems()

        binding.btnViewCart.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCartFragment())
        }
    }

    private fun observeMenuItems() {
        viewModel.menuItems.observe(viewLifecycleOwner) { list ->
            menuItemAdapter.submitList(list)
            binding.btnViewCart.text =
                String.format(resources.getString(R.string.view_cart), list?.sumOf { it.count })
        }
    }
}