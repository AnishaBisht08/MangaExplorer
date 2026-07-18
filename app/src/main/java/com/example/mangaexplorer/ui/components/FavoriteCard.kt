package com.example.mangaexplorer.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mangaexplorer.data.model.FavoriteManga
import com.example.mangaexplorer.ui.theme.Primary
import com.example.mangaexplorer.ui.theme.TextPrimary

@SuppressLint("DefaultLocale")
@Composable
fun FavoriteCard(
    manga: FavoriteManga,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clickable{onClick()},
        shape = RoundedCornerShape(16.dp)
    ){
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = manga.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(110.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                manga.title?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(manga.rating?.toDoubleOrNull()?.div(10)?.let { String.format("%.1f",it) } ?: "N/A")
                }

                Spacer(modifier = Modifier.height(12.dp))

                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

            }
        }
    }

}