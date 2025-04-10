package com.example.wednesdayapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // TODO: Create ui for MainScreen
    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen(onRetry = onRetry)
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
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    // TODO: create retryAction parametr and create UI for ErrorScreen
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error)
        )
        Button(
            onClick = onRetry
        ) {
            Text(text = stringResource(R.string.button_text))
        }
    }
}

@Composable
fun FrogCard(frog: FrogsData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column {
            Text(
                text = "${frog.name}(${frog.type})",
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(frog.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.frog_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = frog.description,
                style = MaterialTheme.typography.bodyLarge
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
    Log.d("MainScreen", "It's Frogs Column")
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier,

    ) {
        items(items = frogs, key = { frog -> frog.name }) { frog ->
            Log.d("MainScreen", "${frog.imgSrc}")
            FrogCard(
                frog = frog,
                modifier = Modifier
                    .padding(all = 15.dp)

            )
        }
    }

}

@Preview
@Composable
fun PreviewSuccessScreen() {
    AppTheme {
        val mockData = List(5) { FrogsData("Great Basin Spadefoot", "", "", "") }
        FrogsColumn(mockData)


    }
}