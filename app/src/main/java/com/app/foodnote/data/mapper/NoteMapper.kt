package com.app.foodnote.data.mapper

import com.app.foodnote.data.data_source.remote.dto.NoteRequestDto
import com.app.foodnote.data.data_source.remote.dto.NoteResponseDto
import com.app.foodnote.data.model.Note

fun NoteResponseDto.toModel() = Note(
    id = this.id,
    content = this.content,
    authorName = this.authorName,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun Note.toRequestDto() = NoteRequestDto(
    content = this.content,
    authorName = this.authorName,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)