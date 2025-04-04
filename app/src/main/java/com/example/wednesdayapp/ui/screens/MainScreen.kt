package com.example.wednesdayapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wednesdayapp.R
import com.example.wednesdayapp.model.FrogsData
import com.example.wednesdayapp.ui.theme.AppTheme

@Composable
fun MainScreen(
    uiState: UiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // TODO: Create ui for MainScreen
    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen()
        is UiState.Success -> FrogsColumn(
            uiState.frogsData, contentPadding = contentPadding, modifier = modifier.fillMaxWidth())
    }

}

/**
 * Screen for displaying loading message
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription =  stringResource(R.string.loading),
        modifier = modifier.size(200.dp)
    )
}

/**
 * Screen for displaying Error Message with re-attempt button
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    // TODO: create retryAction parametr and create UI for ErrorScreen
}

@Composable
fun FrogCard(frog: FrogsData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column {
            Text(
                text = "${frog.name}(${frog.type})",
                style = MaterialTheme.typography.labelMedium
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(frog.imgSrc)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.frog_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = frog.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FrogsColumn(
    frogs: List<FrogsData>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        items(items = frogs, key = { frog -> frog.name }) { frog ->
            FrogCard(
                frog = frog,
                modifier = Modifier

            )
        }
    }

}

@Preview
@Composable
fun PreviewSuccessScreen() {
    AppTheme {

    }
}