package com.tizzone.getaround.presentation.ui.components

import android.graphics.drawable.Drawable
import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.tizzone.getaround.R
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.presentation.theme.GetaroundTheme

@Composable
fun CarCard(
    car: Car,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {

        ConstraintLayout {
            val (image, model, precio, brand, anio) = createRefs()
            CoilImage(
                imageModel = car.picture_url,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(160.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 500),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = painterResource(id = R.drawable.ic_launcher),
                // shows an error ImageBitmap when the request failed.
                error = painterResource(id = R.drawable.ic_launcher),
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(model) {
                        top.linkTo(image.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    },
                text = "modelo: ${car.model}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .constrainAs(brand) {
                        top.linkTo(image.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    },
                text = "marca: ${car.brand}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(precio) {
                        top.linkTo(image.bottom)
                        end.linkTo(parent.end)
                    },
                text = "precio: $ ${car.precio}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .constrainAs(anio) {
                        top.linkTo(image.bottom)
                        end.linkTo(parent.end)
                    },
                text = "año:  ${car.anio}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

