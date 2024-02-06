package com.example.cleanapistruct.domain.model

data class Color(
    val apiUrl: String?,
    val badgeUrl: String?,
    val dateCreated: String?,
    val description: String?,
    val hex: String?,
    val hsv: Hsv?,
    val id: Long?,
    val imageUrl: String?,
    val numComments: Long?,
    val numHearts: Long?,
    val numViews: Long?,
    val numVotes: Long?,
    val rank: Long?,
    val rgb: Rgb?,
    val title: String?,
    val url: String?,
    val userName: String?
) {
    data class Hsv(
        val hue: Int?,
        val saturation: Int?,
        val value: Int?
    )

    data class Rgb(
        val red: Int?,
        val green: Int?,
        val blue: Int?
    )
}