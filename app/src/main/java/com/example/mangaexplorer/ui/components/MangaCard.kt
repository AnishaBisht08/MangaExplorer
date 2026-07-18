package com.example.mangaexplorer.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mangaexplorer.data.model.Manga
import com.example.mangaexplorer.ui.theme.Background
import com.example.mangaexplorer.ui.theme.Primary
import com.example.mangaexplorer.ui.theme.Surface
import com.example.mangaexplorer.ui.theme.TextPrimary
import com.example.mangaexplorer.ui.theme.TextSecondary

@SuppressLint("DefaultLocale")
@Composable
fun MangaCard(
    modifier:Modifier = Modifier,
    manga: Manga,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .width(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        )
    ) {

        Box {

            AsyncImage(
                model = manga.attributes.posterImage?.original ?: manga.attributes.coverImage?.original ,
                contentDescription = manga.attributes.canonicalTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                androidx.compose.ui.graphics.Color.Transparent,
                                androidx.compose.ui.graphics.Color.Transparent,
                                Background.copy(alpha = .65f)
                            )
                        )
                    )
            )

            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopEnd),
                shape = RoundedCornerShape(50),
                color = Surface
            ) {

                Text(
                    text = "⭐ ${manga.attributes.averageRating?.toDoubleOrNull()?.div(10)?.let { String.format("%.1f",it) }  ?: "N/A" }",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    ),
                    color = Primary,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )

            }

        }

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = manga.attributes.canonicalTitle ?: "Unknown Title",
                color = TextPrimary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                manga.attributes.status?.let {

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Primary.copy(alpha = .18f)
                    ) {

                        Text(
                            text = it,
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 4.dp
                            ),
                            color = TextSecondary,
                            style = MaterialTheme.typography.labelSmall
                        )

                    }

                }

            }

        }

    }

}