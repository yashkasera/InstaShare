package com.yashkasera.instashare.ui.scannedtags

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yashkasera.instashare.model.Record

class RecordAdapter: ListAdapter<Record, RecordViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder =
        RecordViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean =
            oldItem._id == newItem._id

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean =
            oldItem.value == newItem.value
    }
}