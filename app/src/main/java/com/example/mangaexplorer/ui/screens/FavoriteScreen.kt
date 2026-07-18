package com.example.mangaexplorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mangaexplorer.data.local.DatabaseProvider
import com.example.mangaexplorer.data.repository.FavoriteRepository
import com.example.mangaexplorer.ui.components.FavoriteCard
import com.example.mangaexplorer.ui.theme.Primary
import com.example.mangaexplorer.ui.theme.TextPrimary
import com.example.mangaexplorer.ui.theme.TextSecondary
import com.example.mangaexplorer.viewmodel.FavoriteViewModel
import com.example.mangaexplorer.viewmodel.FavoriteViewModelFactory

@Composable
fun FavoriteScreen(
    onMangaClick: (Int) -> Unit
) {

    val context = LocalContext.current

    val factory = remember {
        val database = DatabaseProvider.getDatabase(context)
        val repository = FavoriteRepository(database.favoriteDao())
        FavoriteViewModelFactory(repository)
    }

    val viewModel: FavoriteViewModel = viewModel(factory = factory)
    val favorites by viewModel.favorites.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (favorites.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(88.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "No Favorites Yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Save your favorite manga and they'll appear here for quick access.",
                        style = MaterialTheme.typography.titleSmall,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )

                }

            }

        } else {

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(favorites) { manga ->

                    FavoriteCard(
                        manga = manga,
                        onClick = {
                            onMangaClick(manga.id.toInt())
                        },
                        onRemove = {
                            viewModel.deleteFavorite(manga)
                        }
                    )

                }

            }

        }

    }

}