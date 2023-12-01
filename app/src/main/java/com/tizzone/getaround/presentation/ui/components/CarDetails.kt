package com.tizzone.getaround.presentation.ui.components

import android.graphics.drawable.Drawable
import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.tizzone.getaround.R
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.presentation.GoogleMap
import com.tizzone.getaround.presentation.theme.GetaroundTheme

@Composable
fun CarDetails(
    car: Car,
    navigateUp: () -> Unit,
    navigateMap: () -> Unit,
) {
    ConstraintLayout {
        val (brand, image, model, anio, precio, km, motor, garantia, nombrePais, nombreEstado, nombreCapital) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(280.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .zIndex(2f)
                    .clickable {
                        navigateUp()
                    },
                tint = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Map",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding( 10.dp)
                    .zIndex(2f)
                    .clickable {
                        navigateMap()
                    },
                tint = MaterialTheme.colorScheme.primary
            )
            CoilImage(
                imageModel = car.picture_url,
                contentDescription = car.model,
                modifier = Modifier
                    .zIndex(1f)
                    .padding(40.dp)
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 500),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = painterResource(id = R.drawable.ic_launcher),
                // shows an error ImageBitmap when the request failed.
                error = painterResource(id = R.drawable.ic_launcher),
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(brand) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "marca: ${car.brand}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(top = 28.dp)
                .constrainAs(model) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "modelo: ${car.model}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(top = 56.dp)
                .constrainAs(anio) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "año: ${car.anio}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(top = 84.dp)
                .constrainAs(precio) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "precio: ${car.precio}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 112.dp)
                .constrainAs(km) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "kilometraje: ${car.km}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 140.dp)
                .constrainAs(motor) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "motor: ${car.motor}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 168.dp)
                .constrainAs(garantia) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "garantia: ${car.garantia}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 196.dp)
                .constrainAs(nombrePais) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "nombrePais: ${car.nombrePais}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 224.dp)
                .constrainAs(nombreEstado) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "nombreEstado: ${car.nombreEstado}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 252.dp)
                .constrainAs(nombreCapital) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = "nombreCapital: ${car.nombreCapital}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )


    }
}


