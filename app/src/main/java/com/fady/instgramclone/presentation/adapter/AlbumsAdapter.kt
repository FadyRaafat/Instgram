package com.fady.instgramclone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.fady.instgramclone.R
import com.fady.instgramclone.data.models.Album
import com.fady.instgramclone.databinding.ItemTitleBinding
import com.fady.instgramclone.presentation.utils.base.DataBoundListAdapter

class AlbumsAdapter(
    private val onAlbumClicked: (Album) -> Unit
) : DataBoundListAdapter<Album, ItemTitleBinding>(diffCallback = object :
    DiffUtil.ItemCallback<Album>() {
    override fun areContentsTheSame(
        oldItem: Album, newItem: Album
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: Album, newItem: Album
    ): Boolean {
        return oldItem == newItem
    }
}) {


    override fun createBinding(parent: ViewGroup): ItemTitleBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_title, parent, false
        )
    }

    override fun bind(
        binding: ItemTitleBinding, item: Album, position: Int, adapterPosition: Int
    ) {
        binding.titleTV.text = item.title
        binding.root.setOnClickListener { onAlbumClicked.invoke(item) }
    }

}