package com.example.mangaexplorer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mangaexplorer.ui.components.BannerPager
import com.example.mangaexplorer.ui.components.MangaCard
import com.example.mangaexplorer.ui.components.SearchBar
import com.example.mangaexplorer.ui.components.SectionHeader
import com.example.mangaexplorer.ui.navigation.Screen
import com.example.mangaexplorer.ui.theme.Background
import com.example.mangaexplorer.ui.theme.TextPrimary
import com.example.mangaexplorer.viewmodel.MangaViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: MangaViewModel = viewModel()
) {

    val topRatedList by viewModel.topRatedList.collectAsState()
    val popularList by viewModel.popularList.collectAsState()
    val trendingList by viewModel.trendingList.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    var searchQuery by remember {
        mutableStateOf("")
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 12.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            SearchBar(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                onSearch = {
                    if (searchQuery.length >= 3) {
                        viewModel.searchManga(searchQuery)
                    }
                }
            )
        }

        if (searchQuery.isNotBlank()) {

            if (searchResults.isEmpty()) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        text = "No results found",
                        textAlign = TextAlign.Center,
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            } else {

                item {

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        maxItemsInEachRow = 2
                    ) {

                        searchResults.forEach { manga ->
                            MangaCard(
                                modifier = Modifier.weight(1f),
                                manga = manga,
                                onClick = {
                                    navController.navigate(
                                        Screen.Details.createRoute(
                                            manga.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        } else {

            if (topRatedList.isNotEmpty()) {
                item {
                    BannerPager(
                        mangaList = topRatedList
                    )
                }
            }

            item {

                SectionHeader(
                    title = "Popular Manga",
                    onSeeAllClick = {}
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(popularList) { manga ->
                        MangaCard(
                            manga = manga,
                            onClick = {
                                navController.navigate(
                                    Screen.Details.createRoute(
                                        manga.id
                                    )
                                )
                            }
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    title = "Currently Publishing",
                    onSeeAllClick = {}
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(trendingList) { manga ->
                        MangaCard(
                            manga = manga,
                            onClick = {
                                navController.navigate(
                                    Screen.Details.createRoute(
                                        manga.id
                                    )
                                )
                            }
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    title = "Top Rated",
                    onSeeAllClick = {}
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(topRatedList) { manga ->
                        MangaCard(
                            manga = manga,
                            onClick = {
                                navController.navigate(
                                    Screen.Details.createRoute(
                                        manga.id
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}