package com.example.mangaexplorer.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mangaexplorer.data.model.Manga
import com.example.mangaexplorer.ui.theme.Background
import com.example.mangaexplorer.ui.theme.CardColor
import com.example.mangaexplorer.ui.theme.Primary
import com.example.mangaexplorer.ui.theme.Surface
import com.example.mangaexplorer.ui.theme.TextPrimary

@SuppressLint("DefaultLocale")
@Composable
fun FeaturedBanner(
    manga: Manga,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {

        Box {

            AsyncImage(
                model = manga.attributes.coverImage?.original ?: manga.attributes.posterImage?.original,
                contentDescription = manga.attributes.canonicalTitle,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            0.55f to Color.Transparent,
                            0.8f to Color(0x900B1120),
                            1f to Background
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = manga.attributes.canonicalTitle ?: "Unknown Title",
                    color = TextPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Surface
                    ) {

                        Row(
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 7.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "★",
                                color = Primary,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = manga.attributes.averageRating?.toDoubleOrNull()?.div(10)?.let { String.format("%.1f",it) }  ?: "N/A",
                                color = TextPrimary,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )

                        }

                    }

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Primary.copy(alpha = .18f)
                    ) {

                        Text(
                            text = manga.attributes.status ?: "Unknown",
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 7.dp
                            ),
                            color = TextPrimary,
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }

            }

        }

    }

}