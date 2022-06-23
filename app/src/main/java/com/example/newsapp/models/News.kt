package com.example.newsapp.models

import java.io.Serializable

data class News(
    var title: String,
    val createdAt : Long
) : Serializable

