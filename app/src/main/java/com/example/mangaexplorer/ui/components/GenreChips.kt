package com.example.mangaexplorer.ui.components

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mangaexplorer.ui.theme.TextPrimary

@Composable
fun GenreChips(
    genre: List<String?>
) {

    FlowRow {
        genre.filterNotNull()
            .forEach { genre ->
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = genre,
                            color = TextPrimary
                        )
                    }
                )
            }
    }
}