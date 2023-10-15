package com.fady.instgramclone.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fady.instgramclone.R
import com.fady.instgramclone.databinding.FragmentSplashBinding
import com.fady.instgramclone.presentation.utils.base.BaseFragment
import com.fady.instgramclone.presentation.utils.common.showToast
import com.fady.instgramclone.presentation.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentSplash : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun layout(): Int = R.layout.fragment_splash

    override val viewModel: SplashViewModel by viewModels()

    override fun FragmentSplashBinding.initializeUI() {
        viewModel.getUsers()
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getUsersSuccess.collect { apiSuccess ->
                if (apiSuccess) {
                    findNavController().navigate(R.id.action_global_fragmentHome_SingleTop)
                } else {
                    requireActivity().apply {
                        showToast(getString(R.string.no_internet))
                        finish()
                    }
                }
            }
        }
    }
}