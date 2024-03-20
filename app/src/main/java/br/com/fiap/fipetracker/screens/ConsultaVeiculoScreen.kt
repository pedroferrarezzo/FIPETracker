package br.com.fiap.fipetracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.components.ButtonFipeTracker
import br.com.fiap.fipetracker.components.FooterFipeTracker
import br.com.fiap.fipetracker.components.OutlinedTextFieldFipeTracker
import br.com.fiap.fipetracker.components.TextFipeTracker

@Composable
fun ConsultaVeiculoScreen(
    consultaVeiculoScreenViewModel: ConsultaVeiculoScreenViewModel,
    navController: NavController
) {
    val codigoFipe by consultaVeiculoScreenViewModel.codigoFipe.observeAsState(initial = "")
    val isError by consultaVeiculoScreenViewModel.isError.observeAsState(initial = false)
    val context by consultaVeiculoScreenViewModel.context.observeAsState(initial = LocalContext.current)
    val isConnected by consultaVeiculoScreenViewModel.isConnected.observeAsState(initial = true)
    val tipoVeiculo by consultaVeiculoScreenViewModel.tipoVeiculo.observeAsState(initial = -1)

    Box(contentAlignment = Alignment.Center) {

        IconButton(
            onClick = {
                navController.navigate("aboutfipetrackerscreen")
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = colorResource(id = R.color.blue_fipetracker)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(id = R.string.consulta_veiculo_screen_infodescription),
                modifier = Modifier.size(50.dp, 50.dp),
                tint = colorResource(id = R.color.white)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.initialimage),
            contentDescription = stringResource(
                id = R.string.consulta_veiculo_screen_initialimage
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(300.dp, 300.dp)
                .padding(vertical = 30.dp)
        )

        Column(modifier = Modifier.offset(y = 20.dp)) {

            if (isError) {
                if (codigoFipe.isEmpty()) {
                    TextFipeTracker(
                        text = stringResource(id = R.string.consulta_veiculo_screen_fieldrequired),
                        color = colorResource(id = R.color.red_error),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                } else if (tipoVeiculo == 0 || tipoVeiculo == 5 || tipoVeiculo == 8) {
                    TextFipeTracker(
                        text = stringResource(id = R.string.consulta_veiculo_screen_regexfield),
                        color = colorResource(id = R.color.red_error),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                } else {
                    TextFipeTracker(
                        text = stringResource(id = R.string.consulta_veiculo_screen_first_number),
                        color = colorResource(id = R.color.red_error),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }

            OutlinedTextFieldFipeTracker(
                value = codigoFipe,
                placeholder = stringResource(id = R.string.consulta_veiculo_screen_outlinedtextfield),
                onValueChange = {
                    if (it.isNotEmpty()) {
                        when (it[0].toString()) {
                            "0" -> consultaVeiculoScreenViewModel.onTipoVeiculoChanged(0)
                            "5" -> consultaVeiculoScreenViewModel.onTipoVeiculoChanged(5)
                            "8" -> consultaVeiculoScreenViewModel.onTipoVeiculoChanged(8)
                            else -> consultaVeiculoScreenViewModel.onTipoVeiculoChanged(-1)
                        }
                    } else {
                        consultaVeiculoScreenViewModel.onTipoVeiculoChanged(-1)
                    }

                    if (it.length <= 8) {
                        if (
                            !it.matches(Regex("^\\d{6}-\\d\$")) ||
                            tipoVeiculo != 0 &&
                            tipoVeiculo != 5 &&
                            tipoVeiculo != 8
                        ) {
                            consultaVeiculoScreenViewModel.onIsErrorChanged(true)
                        } else {
                            consultaVeiculoScreenViewModel.onIsErrorChanged(false)
                        }

                        if (it.length == 7 && !it.contains("-")) {
                            consultaVeiculoScreenViewModel.onCodigoFipeChanged("$codigoFipe-")
                        } else {
                            consultaVeiculoScreenViewModel.onCodigoFipeChanged(it)
                        }
                    }
                },
                isError = isError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(60.dp),
                trailingIcon = {
                    Row(
                        modifier = Modifier
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        when (tipoVeiculo) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.car_side_solid),
                                contentDescription = stringResource(id = R.string.consulta_veiculo_screen_icon_car),
                                modifier = Modifier.size(25.dp, 25.dp),
                            )

                            5 -> Icon(
                                painter = painterResource(id = R.drawable.truck_solid),
                                contentDescription = stringResource(id = R.string.consulta_veiculo_screen_icon_truck),
                                modifier = Modifier.size(25.dp, 25.dp)
                            )

                            8 -> Icon(
                                painter = painterResource(id = R.drawable.motorcycle_solid),
                                contentDescription = stringResource(id = R.string.consulta_veiculo_screen_icon_motorcycle),
                                modifier = Modifier.size(25.dp, 25.dp)

                            )
                        }

                    }
                }
            )

            ButtonFipeTracker(
                text = stringResource(id = R.string.consulta_veiculo_screen_button_search),
                onValueChange = {

                    if (isError != true) {
                        if (codigoFipe.isEmpty()) {
                            consultaVeiculoScreenViewModel.onIsErrorChanged(true)
                        } else {
                            consultaVeiculoScreenViewModel.verifyInternet(context)
                            navController.navigate(route = "veiculoscreen?codigofipe=${codigoFipe}&isconnected=${isConnected}")
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }

        FooterFipeTracker(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClickBookmarkButton = {
                navController.navigate(route = "listaveiculoscreen")
            },
            onClickSearchButton = {
                navController.navigate(route = "consultaveiculoscreen")
            }
        )
    }
}