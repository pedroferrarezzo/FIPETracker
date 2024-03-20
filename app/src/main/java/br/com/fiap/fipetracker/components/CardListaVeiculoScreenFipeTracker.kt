package br.com.fiap.fipetracker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.model.Veiculo
import coil.compose.AsyncImage
import java.util.Locale

@Composable
fun CardListaVeiculoScreenFipeTracker(
    veiculo: Veiculo, onClickButton: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 5.dp),

        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        border = BorderStroke(2.dp, colorResource(id = R.color.blue_fipetracker)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = veiculo.urlImagemModelo,
                    contentDescription = stringResource(id = R.string.card_lista_veiculo_screen_fipe_tracker_model_image),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(6.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

            Box(
                modifier = Modifier.fillMaxHeight()
            ) {
                TextFipeTracker(
                    text = veiculo.modelo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(vertical = 10.dp)
                        .offset(x = (-5).dp),
                    color = colorResource(id = R.color.gray3)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (-5).dp)
                ) {
                    AsyncImage(
                        model = veiculo.urlImagemMarca,
                        contentDescription = stringResource(id = R.string.card_lista_veiculo_screen_fipe_tracker_manufacturer_image),
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .shadow(6.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds
                    )

                    Column(
                        Modifier.padding(horizontal = 5.dp)
                    ) {
                        TextFipeTracker(
                            text = veiculo.codigoFipe, color = colorResource(id = R.color.gray3)
                        )
                        TextFipeTracker(
                            text = veiculo.mesReferencia.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            color = colorResource(id = R.color.gray3)
                        )
                    }

                }

                ButtonFipeTracker(
                    onValueChange = onClickButton,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                    text = stringResource(id = R.string.button_fipe_tracker_view)
                )
            }
        }
    }
}