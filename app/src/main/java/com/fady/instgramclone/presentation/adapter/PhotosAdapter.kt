package com.fady.instgramclone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.fady.instgramclone.R
import com.fady.instgramclone.data.models.Photo
import com.fady.instgramclone.databinding.ItemThumbnailBinding
import com.fady.instgramclone.presentation.utils.base.DataBoundListAdapter
import com.fady.instgramclone.presentation.utils.common.BindingAdapter

class PhotosAdapter(
    private val onPhotoClicked: (Photo) -> Unit
) : DataBoundListAdapter<Photo, ItemThumbnailBinding>(diffCallback = object :
    DiffUtil.ItemCallback<Photo>() {
    override fun areContentsTheSame(
        oldItem: Photo, newItem: Photo
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: Photo, newItem: Photo
    ): Boolean {
        return oldItem == newItem
    }
}) {


    override fun createBinding(parent: ViewGroup): ItemThumbnailBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_thumbnail, parent, false
        )
    }

    override fun bind(
        binding: ItemThumbnailBinding, item: Photo, position: Int, adapterPosition: Int
    ) {
        binding.thumbnail = item.thumbnailUrl
    }

}