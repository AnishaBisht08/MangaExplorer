package com.example.mangaexplorer.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mangaexplorer.data.local.DatabaseProvider
import com.example.mangaexplorer.data.local.toFavoriteManga
import com.example.mangaexplorer.data.repository.FavoriteRepository
import com.example.mangaexplorer.ui.components.GenreChips
import com.example.mangaexplorer.viewmodel.DetailViewModel
import com.example.mangaexplorer.viewmodel.FavoriteViewModel
import com.example.mangaexplorer.viewmodel.FavoriteViewModelFactory

@SuppressLint("DefaultLocale")
@Composable
fun DetailsScreen(
    id: String?
) {
    val context = LocalContext.current
    val factory = remember {
        val database = DatabaseProvider.getDatabase(context)
        val repository = FavoriteRepository(database.favoriteDao())
        FavoriteViewModelFactory(repository = repository)
    }

    val mangaViewModel: DetailViewModel = viewModel()
    val favViewModel: FavoriteViewModel = viewModel(factory = factory)
    val manga by mangaViewModel.selectedManga.collectAsState()
    val error by mangaViewModel.error.collectAsState()
    val genres by mangaViewModel.genres.collectAsState()

    LaunchedEffect(id) {
        id?.let {
            mangaViewModel.getMangaDetails(it)
            mangaViewModel.loadGenres(id)
        }

    }

    if (error != null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = error!!,
                color = Color.Red
            )
        }
        return
    }


    if (manga == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {


        AsyncImage(
            model = manga?.attributes?.coverImage?.original
                ?: manga?.attributes?.posterImage?.original,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = manga?.attributes?.canonicalTitle ?: "Unknown",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 38.sp
            )


        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            shape = RoundedCornerShape(50),
            tonalElevation = 4.dp
        ) {
            Text(
                text = "⭐ ${manga?.attributes?.averageRating?.toDoubleOrNull()?.div(10)?.let { String.format("%.1f",it) }  ?: "N/A" }",
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        GenreChips(genre = genres)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Status :  ${manga?.attributes?.status ?: "Unknown"}" ,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Synopsis",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(12.dp))

         Text(text = manga?.attributes?.synopsis ?: "No synopsis available")

        Spacer(modifier = Modifier.height(24.dp))

        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                manga?.let {
                    favViewModel.insertFavorite(
                        it.toFavoriteManga()
                    )
                }
            }
        ) {

            Icon(Icons.Default.Favorite, null)

            Spacer(Modifier.width(8.dp))

            Text("Add to Favorites")

        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val url = "https://kitsu.app/manga/${manga?.attributes?.slug}"
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                context.startActivity(intent)
            }
        ) {
            Text("View on Kitsu")
        }

    }
}