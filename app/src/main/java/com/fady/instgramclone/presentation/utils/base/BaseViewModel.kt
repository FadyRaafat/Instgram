package com.fady.instgramclone.presentation.utils.base

import androidx.lifecycle.ViewModel
import com.fady.instgramclone.presentation.utils.common.Resource
import com.fady.instgramclone.presentation.utils.common.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    var showLoading = SingleLiveEvent<Boolean>()
    var showApiError = SingleLiveEvent<Resource.Failure>()
}