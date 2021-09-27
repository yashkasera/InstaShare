package com.yashkasera.instashare.util

import android.content.Context
import android.content.Intent
import android.net.Uri

class Functions {
    companion object {
        fun openURL(context: Context, url: String?, packageName: String? = null) {
            try {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(url)
                    it.setPackage(packageName)
                    context.startActivity(it)
                }
            } catch (e: Exception) {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(url)
                    context.startActivity(it)
                }
                e.printStackTrace()
            }
        }
    }
}