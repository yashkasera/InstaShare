package com.yashkasera.instashare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.yashkasera.instashare.databinding.ActivityMainBinding
import com.yashkasera.instashare.ui.MainFragment
import com.yashkasera.instashare.ui.ScanFragment
import com.yashkasera.instashare.ui.SettingsFragment


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pagerAdapter = ViewPagerAdapter(this)
        binding.apply {
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = 1
            settings.setOnClickListener {
                viewPager.currentItem = 2
            }
            scan.setOnClickListener {
                viewPager.currentItem = 0
            }
        }
    }

    private inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int) = when (position) {
            0 -> ScanFragment()
            1 -> MainFragment()
            else -> SettingsFragment()
        }
    }

    var exitCount: Int = 0
    override fun onBackPressed() {
        when (binding.viewPager.currentItem) {
            1 -> {
                exitCount++
                Snackbar.make(
                    this@MainActivity,
                    findViewById(android.R.id.content),
                    "Press back again to exit",
                    BaseTransientBottomBar.LENGTH_SHORT
                ).addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                        exitCount = 0
                    }
                }).show()
                if (exitCount == 2)
                    super.onBackPressed()
            }
            else -> binding.viewPager.currentItem = 1
        }
    }
}