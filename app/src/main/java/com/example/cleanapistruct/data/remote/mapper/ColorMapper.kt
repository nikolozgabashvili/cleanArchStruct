package com.example.cleanapistruct.data.remote.mapper

import com.example.cleanapistruct.data.remote.model.ColorDto
import com.example.cleanapistruct.domain.model.Color

fun ColorDto.RgbDto.toRgb(): Color.Rgb {
    val r = this.red
    val g = this.green
    val b = this.blue
    return Color.Rgb(r, g, b)
}

fun ColorDto.HsvDto.toHsv(): Color.Hsv {
    val h = hue
    val s = saturation
    val v = value
    return Color.Hsv(h, s, v)
}

fun ColorDto.toColor(): Color {
    val hsv = hsv?.toHsv()
    val rgb = rgb?.toRgb()

    return Color(
        apiUrl = apiUrl,
        badgeUrl = badgeUrl,
        dateCreated = dateCreated,
        description = description,
        hex = hex,
        hsv = hsv,
        id = id,
        imageUrl = imageUrl,
        numComments = numHearts,
        rgb = Color.Rgb(
            this.rgb?.red,
            this.rgb?.green,
            this.rgb?.blue
        ),
        numHearts = numHearts,
        numViews = numViews,
        numVotes = numVotes,
        rank = rank,
        title = title,
        url = url,
        userName = userName
    )
}