package com.example.cleanapistruct.common.constants

object Constants {
    const val GET_ITEM_URL = "api/colors/new?format=json"
    var _keyword: String? = null
    private val key get() = _keyword!!
    const val GET_ITEM_WITH_NAME = "api/colors/new?&format=json"
}