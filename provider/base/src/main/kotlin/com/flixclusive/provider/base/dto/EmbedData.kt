package com.flixclusive.provider.base.dto

import com.flixclusive.model.provider.SourceLink
import com.flixclusive.model.provider.Subtitle

data class EmbedData(
    val servers: List<SourceLink>,
    val subtitles: List<Subtitle> = emptyList()
)