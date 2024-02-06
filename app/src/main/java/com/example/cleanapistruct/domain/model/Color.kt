package com.example.cleanapistruct.domain.model

class Color(
    val id: Long?,
    val title: String?,
    val userName: String?,
    val numViews: Long?,
    val numComments: Long?,
    val numHearts: Long?,
    val rank: Long?,
    val dateCreated: String?,
    val hex: String?,
    val rgb: RGB?,
    val hsv: HSV?,
    val description: String?,
    val url: String?,
    val imageUrl: String?,
    val badgeUrl: String?,
    val apiUrl: String?

) {
    data class RGB(
        val red: Int?, val green: Int?, val blue: Int?
    )

    data class HSV(
        val hue: Int?, val saturation: Int?, val value: Int?

    )
}