@file:OptIn(ExperimentalSerializationApi::class)

package com.alexcao.starpx.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class GetImageSetSummariesResponse(
    val getImageSetSummaries: GetImageSetSummaries
)

@Serializable
data class GetImageSetSummaries(
    val nextToken: String?,
    @JsonNames("image_sets")
    val imageSets: List<ImageSet>
)

@Serializable
data class ImageSet(
    val caption: String,
    @JsonNames("set_id")
    val setId: String,
    val state: String,
    @JsonNames("image_detail")
    val imageDetail: ImageDetail
)

@Serializable
data class ImageDetail(
    @JsonNames("full_height")
    val fullHeight: Int,
    @JsonNames("full_url")
    val fullUrl: String,
    @JsonNames("full_width")
    val fullWidth: Int,
    val thumbs: Thumbs
)

@Serializable
data class Thumbs(
    val xlarge: String,
    val large: String,
    val small: String,
    val medium: String
)
