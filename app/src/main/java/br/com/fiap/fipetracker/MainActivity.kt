package br.com.fiap.fipetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.fipetracker.screens.AboutFipeTrackerScreen
import br.com.fiap.fipetracker.screens.ConsultaVeiculoScreen
import br.com.fiap.fipetracker.screens.ConsultaVeiculoScreenViewModel
import br.com.fiap.fipetracker.screens.ListaVeiculoScreen
import br.com.fiap.fipetracker.screens.ListaVeiculoScreenViewModel
import br.com.fiap.fipetracker.screens.VeiculoScreen
import br.com.fiap.fipetracker.screens.VeiculoScreenViewModel
import br.com.fiap.fipetracker.ui.theme.FIPETrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FIPETrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "consultaveiculoscreen",
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                tween(1000)
                            ) + fadeIn(
                                animationSpec = tween(1000)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                tween(1000)
                            ) + fadeOut(
                                animationSpec = tween(1000)
                            )
                        }
                    ) {

                        composable(route = "consultaveiculoscreen") {
                            ConsultaVeiculoScreen(
                                consultaVeiculoScreenViewModel = ConsultaVeiculoScreenViewModel(),
                                navController = navController
                            )
                        }

                        composable(
                            route = "veiculoscreen?codigofipe={codigofipe}&mesreferencia={mesreferencia}&isloadingstatus={isloadingstatus}&isconnected={isconnected}&savedvehicle={savedvehicle}",
                            arguments = listOf(
                                navArgument(name = "codigofipe") {
                                    defaultValue = ""
                                },
                                navArgument(name = "mesreferencia") {
                                    defaultValue = ""
                                },
                                navArgument(name = "isloadingstatus") {
                                    defaultValue = true
                                },
                                navArgument(name = "isconnected") {
                                    defaultValue = true
                                },
                                navArgument(name = "savedvehicle") {
                                    defaultValue = false
                                }
                            )
                        ) {
                            val codigoFipe = it.arguments?.getString("codigofipe")
                            val mesReferencia = it.arguments?.getString("mesreferencia")
                            val isLoadingStatus = it.arguments?.getBoolean("isloadingstatus")
                            val isConnected = it.arguments?.getBoolean("isconnected")
                            val savedVehicle = it.arguments?.getBoolean("savedvehicle")

                            VeiculoScreen(
                                veiculoScreenViewModel = VeiculoScreenViewModel(),
                                navController = navController,
                                codigoFipe = codigoFipe!!,
                                isLoadingStatus = isLoadingStatus!!,
                                mesReferencia = mesReferencia!!,
                                isConnected = isConnected!!,
                                savedVehicle = savedVehicle!!
                            )
                        }

                        composable(route = "listaveiculoscreen") {
                            ListaVeiculoScreen(
                                listaVeiculoScreenViewModel = ListaVeiculoScreenViewModel(),
                                navController = navController
                            )
                        }

                        composable(
                            route = "aboutfipetrackerscreen",
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                    tween(1000)
                                ) + fadeIn(
                                    animationSpec = tween(1000)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                    tween(1000)
                                ) + fadeOut(
                                    animationSpec = tween(1000)
                                )
                            }) {
                            AboutFipeTrackerScreen(
                                navController = navController
                            )
                        }

                    }
                }
            }
        }
    }
}
