package com.fady.instgramclone.presentation.fragments

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.fady.instgramclone.R
import com.fady.instgramclone.data.models.Photo
import com.fady.instgramclone.data.models.PhotosResponse
import com.fady.instgramclone.databinding.FragmentGalleryBinding
import com.fady.instgramclone.presentation.adapter.PhotosAdapter
import com.fady.instgramclone.presentation.utils.base.BaseFragment
import com.fady.instgramclone.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class FragmentGallery : BaseFragment<FragmentGalleryBinding, HomeViewModel>() {

    override fun layout(): Int = R.layout.fragment_gallery

    override val viewModel: HomeViewModel by navGraphViewModels(R.id.nav_graph) { defaultViewModelProviderFactory }

    private val photosAdapter by lazy {
        PhotosAdapter {}
    }

    override fun FragmentGalleryBinding.initializeUI() {
        viewModel.getPhotos()
        binding.searchPhotosSV.doAfterTextChanged {
            viewModel.searchPhotos(it.toString())
        }
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photosSuccess.collect { apiSuccess ->
                if (apiSuccess) {
                    setPhotosAdapter(viewModel.getPhotosResponse())
                }
            }
        }
    }

    private fun setPhotosAdapter(photosResponse: ArrayList<Photo>?) {
        binding.photosRV.adapter = photosAdapter
        photosAdapter.submitList(photosResponse)
    }
}