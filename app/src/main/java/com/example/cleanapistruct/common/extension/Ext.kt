@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.cleanapistruct.common.extension

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun View.loadBackground(url: String) {
    Glide.with(this).load(url).into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            background = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {

        }

    })
}

fun String.formatDt(): String {
    val lst2 = when (this.substring(this.length - 2, this.length)) {
        "01" -> "იან"
        "02" -> "თებ"
        "03" -> "მარ"
        "04" -> "აპრ"
        "05" -> "მაი"
        "06" -> "ინვ"
        "07" -> "ივლ"
        "08" -> "აგვ"
        "09" -> "სექ"
        "10" -> "ოქტ"
        "11" -> "ნოე"
        "12" -> "დეკ"

        else -> {
            Unit
        }
    }
    return this.substring(0, this.length - 2) + lst2
}