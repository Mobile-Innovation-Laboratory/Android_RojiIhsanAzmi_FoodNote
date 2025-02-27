package com.app.foodnote.data.model


import com.app.foodnote.data.model.Meal
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)