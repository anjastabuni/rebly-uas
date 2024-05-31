package com.example.rebly_uas

import com.google.firebase.database.Exclude

data class ItemData(
    val gambar:String? = null,
    val nama:String? = null,
    val desc:String? = null,
//    val bio:String? = null,
    @get:Exclude
    @set:Exclude
    var key:String? = null
)
