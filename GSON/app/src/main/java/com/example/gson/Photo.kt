package com.example.gson

data class Photo (
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,


    ) {
    override fun toString(): String {
        return "{\"id\":\"$id\", \"owner\":\"$owner\", \"secret\":\"$secret\", \"server\":\"$server\", \"farm\":\"$farm\", \"title\":\"$title\", \"ispublic\":$ispublic, \"isfriend\":$isfriend, \"isfamily\":$isfamily}"
    }
}