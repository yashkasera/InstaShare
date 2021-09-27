package com.yashkasera.instashare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yashkasera.instashare.R
import com.yashkasera.instashare.databinding.ContentMainBinding
import com.yashkasera.instashare.model.Record
import com.yashkasera.instashare.ui.scannedtags.RecordAdapter
import com.yashkasera.instashare.util.RecordType
import java.util.*

class MainFragment : Fragment() {

    private lateinit var binding: ContentMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var items: Array<ImageButton>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet.bottomSheet)
            tabLayout.apply {
                items = arrayOf(item1, item2, item3, item4, item5)
                for (item in items)
                    item.setOnClickListener { selectTab(item) }
            }
            bottomSheet.textView.setOnClickListener {
                val state =
                    if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                        BottomSheetBehavior.STATE_COLLAPSED
                    } else
                        BottomSheetBehavior.STATE_EXPANDED
                bottomSheetBehavior.state = state
            }
            val list = listOf(
                Record(0L, RecordType.PHONE, "+919xxxxxxx05", Date().toString()),
                Record(1L, RecordType.INSTAGRAM, "yash.kasera", Date().toString()),
                Record(2L, RecordType.WHATSAPP, "+919xxxxxxx05", Date().toString()),
                Record(3L, RecordType.SNAPCHAT, "yash.kasera", Date().toString()),
                Record(
                    4L,
                    RecordType.LINK,
                    "https://yashkasera.github.io/WatSightsWeb",
                    Date().toString()
                ),
            )
            RecordAdapter().also {
                binding.bottomSheet.recyclerView.adapter = it
                it.submitList(list)
            }

        }
    }

    private fun selectTab(item: ImageButton) {
        binding.tabLayout.apply {
            for (i in items) {
                i.setBackgroundResource(0)
                i.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
            }
            item.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            when (item) {
                items.first() -> item.setBackgroundResource(R.drawable.tab_background_selector_left)
                items.last() -> item.setBackgroundResource(R.drawable.tab_background_selector_right)
                else -> item.setBackgroundResource(R.drawable.tab_background_selector_center)
            }
            binding.text.text =
                when (item) {
                    items[0] -> "+919xxxxxxx05"
                    items[1] -> "@yash.kasera"
                    items[2] -> "+919xxxxxxx05"
                    items[3] -> "@yash.kasera"
                    else -> "https://github.com/yashkasera"
                }
        }
    }
}