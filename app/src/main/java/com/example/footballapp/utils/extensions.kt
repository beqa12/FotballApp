package com.example.footballapp.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebSettings
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.footballapp.R


fun AppCompatImageView.loadImage(imageUrl: String, isRedCard: Boolean = false) {

    val url = GlideUrl(
        imageUrl, LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
            .build()
    )
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
        .apply(RequestOptions.centerCropTransform())
        .transform(CircleCrop())
        .into(this)
}


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}