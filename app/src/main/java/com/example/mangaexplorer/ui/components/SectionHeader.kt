package com.example.mangaexplorer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mangaexplorer.ui.theme.PrimaryDark
import com.example.mangaexplorer.ui.theme.TextPrimary

@Composable
fun SectionHeader(
    title: String,
    onSeeAllClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {

        Text(
            text = title,
            color = TextPrimary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Left
        )

        Text(
            "See All",
            textAlign = TextAlign.Right,
            color = PrimaryDark,
            modifier = Modifier.clickable(onClick = onSeeAllClick)
        )
    }
}