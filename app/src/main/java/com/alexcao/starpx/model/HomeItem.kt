package com.alexcao.starpx.model

data class HomeItem(
    val id: String,
    val thumbnail: String,
    val full: String,
) {
    companion object {
        val mockData: List<HomeItem> = listOf(
            HomeItem(
                id = "1",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "2",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "3",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "4",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "5",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "6",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "7",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "8",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "9",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
            HomeItem(
                id = "10",
                thumbnail = "https://via.placeholder.com/150",
                full = "https://via.placeholder.com/600"
            ),
        )
    }
}