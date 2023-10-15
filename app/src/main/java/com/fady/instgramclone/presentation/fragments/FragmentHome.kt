package com.fady.instgramclone.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.fady.instgramclone.R
import com.fady.instgramclone.databinding.FragmentHomeBinding
import com.fady.instgramclone.databinding.FragmentSplashBinding
import com.fady.instgramclone.presentation.adapter.AlbumsAdapter
import com.fady.instgramclone.presentation.utils.base.BaseFragment
import com.fady.instgramclone.presentation.utils.common.makeTitleValueSpannable
import com.fady.instgramclone.presentation.utils.common.showToast
import com.fady.instgramclone.presentation.viewmodels.HomeViewModel
import com.fady.instgramclone.presentation.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun layout(): Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by navGraphViewModels(R.id.nav_graph) { defaultViewModelProviderFactory }

    override fun FragmentHomeBinding.initializeUI() {
        binding.apply {
            viewModel.getUserDetails().let {
                userEmailTV.text =
                    requireContext().makeTitleValueSpannable(getString(R.string.email), it?.email)
                userNameTV.text =
                    requireContext().makeTitleValueSpannable(getString(R.string.name), it?.name)
                userPhoneTV.text =
                    requireContext().makeTitleValueSpannable(getString(R.string.phone), it?.phone)
                userCompanyTV.text = requireContext().makeTitleValueSpannable(
                    getString(R.string.company), it?.company?.name
                )
            }
        }
        viewModel.getAlbums()
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.albumsSuccess.collect { apiSuccess ->
                if (apiSuccess) {
                    setAlbumsAdapter()
                }
            }
        }
    }

    private fun setAlbumsAdapter() {
        val albumsAdapter = AlbumsAdapter { album ->
            album.id?.let { viewModel.setAlbumId(it) }
            findNavController().navigate(R.id.action_global_fragmentGallery)
        }
        binding.albumsRV.apply {
            adapter = albumsAdapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
        albumsAdapter.submitList(viewModel.getAlbumsResponse())
    }
}