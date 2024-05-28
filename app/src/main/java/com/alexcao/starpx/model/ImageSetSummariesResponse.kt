data class ImageSetSummariesResponse(
    val data: ImageSetSummariesData
)

data class ImageSetSummariesData(
    val getImageSetSummaries: ImageSetSummariesResult
)

data class ImageSetSummariesResult(
    val nextToken: String,
    val imageSets: List<ImageSet>
)

data class ImageSet(
    val caption: String,
    val setId: String,
    val state: String,
    val imageDetail: ImageDetail
)

data class ImageDetail(
    val fullHeight: Int,
    val fullUrl: String,
    val fullWidth: Int,
    val thumbs: Thumbs
)

data class Thumbs(
    val xlarge: String,
    val large: String,
    val small: String,
    val medium: String
)
