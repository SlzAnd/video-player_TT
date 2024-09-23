package com.andrews.videoplayer.ui.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun VideoPreviewItem(
    modifier: Modifier = Modifier,
    thumbUrl: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                alpha = 1f,
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(context)
                    .data(thumbUrl)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = "Thumb"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}