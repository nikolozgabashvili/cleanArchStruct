package com.example.cleanapistruct.data.remote.mapper

import com.example.cleanapistruct.data.remote.model.ColorDto
import com.example.cleanapistruct.domain.model.Color

fun ColorDto.RGBDto.toRgb(): Color.RGB {
    return Color.RGB(red, green, blue)
}

fun ColorDto.HSVDto.toHsv(): Color.HSV {
    return Color.HSV(hue, saturation, value)
}

fun ColorDto.toColor(): Color {

    val hsv = hsv?.toHsv()
    val rgb = rgb?.toRgb()


    return Color(
        id,
        title,
        userName,
        numViews,
        numComments,
        numHearts,
        rank,
        dateCreated,
        hex,
        rgb,
        hsv,
        description,
        url,
        imageUrl,
        badgeUrl,
        apiUrl


    )
}