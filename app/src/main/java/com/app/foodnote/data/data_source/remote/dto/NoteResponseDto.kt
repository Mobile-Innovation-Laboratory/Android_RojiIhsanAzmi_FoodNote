package com.app.foodnote.data.data_source.remote.dto

import androidx.annotation.Keep
import com.app.foodnote.data.data_source.remote.dto.ErrorResponseDto
import com.google.gson.annotations.SerializedName

@Keep
data class NoteResponseDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("authorName") val authorName: String? = null,
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("updatedAt") val updatedAt: String? = null,

    @SerializedName("error") val error: ErrorResponseDto? = null,
)