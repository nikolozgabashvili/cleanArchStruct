package com.example.cleanapistruct.data.remote.model

data class ColorDto(
    val apiUrl: String?,
    val badgeUrl: String?,
    val dateCreated: String?,
    val description: String?,
    val hex: String?,
    val hsv: HsvDto?,
    val id: Long?,
    val imageUrl: String?,
    val numComments: Long?,
    val numHearts: Long?,
    val numViews: Long?,
    val numVotes: Long?,
    val rank: Long?,
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
        val red: Int?,
        val green: Int?,
        val blue: Int?
    )
}