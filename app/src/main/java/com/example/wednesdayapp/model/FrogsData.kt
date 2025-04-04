package com.example.wednesdayapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a frogs data which includes name, type, description and image URL
 */
@Serializable
data class FrogsData(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
