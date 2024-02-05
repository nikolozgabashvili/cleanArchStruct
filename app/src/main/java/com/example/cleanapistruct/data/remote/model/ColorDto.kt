package com.example.cleanapistruct.data.remote.model

data class ColorDto(
    val apiUrl: String?,
    val badgeUrl: String?,
    val dateCreated: String?,
    val description: String?,
    val hex: String?,
    val hsv: HsvDto?,
    val id: Int?,
    val imageUrl: String?,
    val numComments: Int?,
    val numHearts: Int?,
    val numViews: Int?,
    val numVotes: Int?,
    val rank: Int?,
    val rgb: RgbDto?,
    val title: String?,
    val url: String?,
    val userName: String?
) {
    data class HsvDto(
        val hue: Int?,
        val saturation: Int?,
        val value: Int?
    )

    data class RgbDto(
        val blue: Int?,
        val green: Int?,
        val red: Int?
    )
}