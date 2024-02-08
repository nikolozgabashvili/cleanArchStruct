package com.example.cleanapistruct.extension

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cleanapistruct.R

fun View.loadImage(

    url: String
) {
    Glide.with(this.context).load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            background = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            //
        }

    })
}