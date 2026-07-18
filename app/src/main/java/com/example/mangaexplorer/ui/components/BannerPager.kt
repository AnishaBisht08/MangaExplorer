package com.example.mangaexplorer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mangaexplorer.data.model.Manga

@Composable
fun BannerPager(
    mangaList: List<Manga>
) {

    val featuredManga = mangaList.take(5)
    val pagerState = rememberPagerState(
        pageCount = {featuredManga.size}
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        FeaturedBanner(
            manga = featuredManga[page]
        )
    }
}