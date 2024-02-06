package com.example.cleanapistruct.data.remote.model

class ColorDto(

    val id: Long?,
    val title: String?,
    val userName: String?,
    val numViews: Long?,
    val numComments: Long?,
    val numHearts: Long?,
    val rank: Long?,
    val dateCreated: String?,
    val hex: String?,
    val rgb: RGBDto?,
    val hsv: HSVDto?,
    val description: String?,
    val url: String?,
    val imageUrl: String?,
    val badgeUrl: String?,
    val apiUrl: String?

) {
    data class RGBDto(
        val red: Int?, val green: Int?, val blue: Int?
    )

    data class HSVDto(
        val hue: Int?, val saturation: Int?, val value: Int?

    )
}
