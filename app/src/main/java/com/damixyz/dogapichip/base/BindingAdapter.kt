package com.damixyz.dogapichip.base

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.damixyz.dogapichip.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imgUrl: String?) {

    val uri: Uri = Uri.parse(imgUrl ?: "")


    Glide.with(view.context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_baseline_broken_image_24)
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
