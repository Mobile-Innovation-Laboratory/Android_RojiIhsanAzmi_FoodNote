package com.app.foodnote.data.data_source.remote.dto

import androidx.annotation.Keep
import com.app.foodnote.data.data_source.remote.dto.ErrorResponseDto
import com.google.gson.annotations.SerializedName

@Keep
data class NoteDeleteResponseDto(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("error") val error: ErrorResponseDto? = null,
)


