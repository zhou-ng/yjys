package com.example.yjys.model

data class StandardMapResponse(val code: Int, val msg: String, val data: List<MapData>)

data class MapData(val name: String, val content: String, val imgUrl: String, val url: String)