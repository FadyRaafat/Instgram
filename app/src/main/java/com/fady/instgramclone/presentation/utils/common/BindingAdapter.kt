package com.fady.instgramclone.presentation.utils.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

object BindingAdapter {


    @JvmStatic
    @BindingAdapter("setNetworkImage")
    fun setNetworkImage(view: ImageView, image: String?) {
        if (image == null) return
        val options = RequestOptions().override(650, 375)
        Glide.with(view.context).load(image).apply(options).into(view)
    }

}