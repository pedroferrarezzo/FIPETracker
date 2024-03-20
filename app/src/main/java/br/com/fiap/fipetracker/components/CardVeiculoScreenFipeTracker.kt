package br.com.fiap.fipetracker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.model.Veiculo
import java.util.Locale

@Composable
fun CardVeiculoScreenFipeTracker(
    listVeiculoFIPE: List<Veiculo>,
    indexListVeiculoFPE: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {

            Card(
                modifier = Modifier.size(110.dp, 90.dp),
                border = BorderStroke(2.dp, colorResource(id = R.color.blue_fipetracker)),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextFipeTracker(
                        text = stringResource(id = R.string.card_veiculo_screen_fipe_tracker_year_model),
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center
                    )
                    TextFipeTracker(
                        text = "${listVeiculoFIPE[indexListVeiculoFPE].anoModelo}",
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )

                }
            }

            Card(
                modifier = Modifier.size(110.dp, 90.dp),
                border = BorderStroke(2.dp, colorResource(id = R.color.blue_fipetracker)),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextFipeTracker(
                        text = stringResource(id = R.string.card_veiculo_screen_fipe_tracker_month),
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center
                    )
                    TextFipeTracker(
                        text = listVeiculoFIPE[indexListVeiculoFPE].mesReferencia.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier.size(110.dp, 90.dp),
                border = BorderStroke(2.dp, colorResource(id = R.color.blue_fipetracker)),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextFipeTracker(
                        text = stringResource(id = R.string.card_veiculo_screen_fipe_tracker_fuel),
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center
                    )

                    TextFipeTracker(
                        text = listVeiculoFIPE[indexListVeiculoFPE].siglaCombustivel,
                        color = colorResource(id = R.color.blue_fipetracker),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )

                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .height(100.dp),
            border = BorderStroke(2.dp, colorResource(id = R.color.blue_fipetracker)),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = stringResource(id = R.string.card_veiculo_screen_fipe_tracker_icon_money),
                    tint = colorResource(id = R.color.blue_fipetracker)
                )
                TextFipeTracker(
                    text = stringResource(id = R.string.card_veiculo_screen_fipe_tracker_value),
                    color = colorResource(id = R.color.blue_fipetracker),
                    textAlign = TextAlign.Center
                )
                TextFipeTracker(
                    text = listVeiculoFIPE[indexListVeiculoFPE].valor,
                    color = colorResource(id = R.color.blue_fipetracker),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}



