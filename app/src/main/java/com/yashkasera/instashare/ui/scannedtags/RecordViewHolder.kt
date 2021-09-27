package com.yashkasera.instashare.ui.scannedtags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yashkasera.instashare.R
import com.yashkasera.instashare.databinding.ItemRecordBinding
import com.yashkasera.instashare.model.Record
import com.yashkasera.instashare.util.Functions.Companion.openURL
import com.yashkasera.instashare.util.RecordType


class RecordViewHolder(private val binding: ItemRecordBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(record: Record) {
        binding.apply {
            value.text = record.value
            when (record.type) {
                RecordType.PHONE -> {
                    setType(R.string.phone, R.drawable.ic_phone)
                }
                RecordType.INSTAGRAM -> {
                    setType(R.string.instagram, R.drawable.ic_instagram)
                    icon.setOnClickListener {
                        openURL(
                            context = root.context,
                            url = "https://instagram.com/${record.value}",
                            packageName = "com.instagram.android"
                        )
                    }
                }
                RecordType.WHATSAPP -> {
                    setType(R.string.whatsapp, R.drawable.ic_whatsapp)
                    openURL(
                        context = root.context,
                        url = "http://api.whatsapp.com/send?phone=${record.value}",
                        packageName = "com.whatsapp"
                    )
                }
                RecordType.SNAPCHAT -> {
                    setType(R.string.snapchat, R.drawable.ic_snapchat)
                    openURL(
                        context = root.context,
                        url = "https://snapchat.com/add/${record.value}",
                        packageName = "com.snapchat.android"
                    )
                }
                RecordType.LINK -> {
                    setType(R.string.link, R.drawable.ic_link)
                    openURL(
                        context = root.context,
                        url = record.value
                    )
                }
            }
        }
    }

    private fun setType(stringId: Int, drawable: Int) {
        binding.apply {
            type.text = root.context.getString(stringId)
            icon.setImageDrawable(
                ContextCompat.getDrawable(
                    root.context, drawable
                )
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup): RecordViewHolder {
            return RecordViewHolder(
                ItemRecordBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
