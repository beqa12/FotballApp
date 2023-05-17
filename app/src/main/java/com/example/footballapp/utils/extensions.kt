package com.example.footballapp.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.footballapp.R


fun AppCompatImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.color.white)
        .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}
