package com.tizzone.getaround.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tizzone.getaround.presentation.route.Screen
import com.tizzone.getaround.presentation.theme.GetaroundTheme
import com.tizzone.getaround.presentation.ui.CarsViewModel
import com.tizzone.getaround.presentation.ui.screens.CarListScreen
import com.tizzone.getaround.presentation.ui.screens.CardDetailsScreen
import com.tizzone.getaround.utils.CARS_VIEWMODEL
import com.tizzone.getaround.utils.CAR_INDEX
import com.tizzone.getaround.utils.CAR_INDEX_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import com.tizzone.getaround.R


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetaroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CarsList.route
                    ) {
                        composable(route = Screen.CarsList.route) { navBackStackEntry ->
                            val factory =
                                HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                            val viewModel: CarsViewModel =
                                viewModel(key = CARS_VIEWMODEL, factory = factory)
                            CarListScreen(
                                viewModel = viewModel,
                                onNavigateToCarDetail = navController::navigate
                            )
                        }
                        composable(
                            route = Screen.CarDetail.route + CAR_INDEX_URL,
                            arguments = listOf(
                                navArgument(CAR_INDEX) {
                                    type = NavType.IntType
                                }
                            )
                        ) { navBackStackEntry ->
                            val factory =
                                HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                            val viewModel: CarsViewModel =
                                viewModel(key = CARS_VIEWMODEL, factory = factory)



                            CardDetailsScreen(
                                viewModel = viewModel,
                                index = navBackStackEntry.arguments?.getInt(CAR_INDEX),
                                navigateUp = { navController.popBackStack() },
                                navigateMap = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun GoogleMap() {
    val mapView = rememberMapViewWithLifeCycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AndroidView(
            {mapView}
        ) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                val p1 = LatLng(21.6345, -100.5528)
                val p2 = LatLng(19.5680, -98.2653)
                val p3 = LatLng(17.5680, -99.2653)
                val p4 = LatLng(18.5680, -97.2653)
                val p5 = LatLng(19.0080, -96.2653)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(p2, 6f))
                val markerP1 =  MarkerOptions()
                    .title("p1")
                    .position(p1)
                map.addMarker(markerP1)
                val markerP2 = MarkerOptions()
                    .title("p2")
                    .position(p2)
                map.addMarker(markerP2)
                val markerP3 = MarkerOptions()
                    .title("p3")
                    .position(p3)
                map.addMarker(markerP3)
                val markerP4 = MarkerOptions()
                    .title("p4")
                    .position(p4)
                map.addMarker(markerP4)
                val markerP5 = MarkerOptions()
                    .title("p5")
                    .position(p5)
                map.addMarker(markerP5)
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifeCycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = com.google.maps.android.ktx.R.id.map_frame
        }
    }
    val lifeCycleObserver = rememberMapLifecycleObserver(mapView)
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifeCycle) {
        lifeCycle.addObserver(lifeCycleObserver)
        onDispose {
            lifeCycle.removeObserver(lifeCycleObserver)
        }
    }

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }

