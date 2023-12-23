package com.example.marsphotosstarter.ui.theme.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marsphotosstarter.R
import com.example.marsphotosstarter.network.MarsPhoto
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.reflect.KFunction1


@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier,
    retryAction: KFunction1<MarsViewModel, Unit> = MarsViewModel::getMarsPhotos

) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success -> PhotosGridScreen(marsUiState.photos, modifier = modifier)
        MarsUiState.Error -> ErrorScreen( retryAction, modifier = modifier.fillMaxSize())

    }

}
//
//@Composable
//fun ResultScreen(
//    photos: String,
//    modifier: Modifier = Modifier
//) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//    ) {
//        Text(text = photos)
//    }
//}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
//    horizontalAlignment = Alignment.Center,
//    verticalAlignment = Alignment.Center

    ) {
    Image(
        modifier = modifier.size(height = 70.dp, width = 70.dp),
        painter = painterResource(R.drawable.loadingimage),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.close), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun MarsPhotoCard( photo: MarsPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.img_src)
            .crossfade(true)
            .build(),
            error = painterResource(R.drawable.errorimage),
            placeholder = painterResource(R.drawable.loadingimage),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.marsPhoto),
            modifier = Modifier.fillMaxWidth()
        )
    }

}



@Composable
fun PhotosGridScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }){
            photo ->    MarsPhotoCard(
            photo,
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
                )
        }
    }
}

