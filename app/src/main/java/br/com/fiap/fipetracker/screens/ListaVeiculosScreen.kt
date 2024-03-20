package br.com.fiap.fipetracker.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.components.CardListaVeiculoScreenFipeTracker
import br.com.fiap.fipetracker.components.ErrorComponentFipeTracker
import br.com.fiap.fipetracker.components.IconButtonReturnFipeTracker
import br.com.fiap.fipetracker.components.TextFipeTracker
import br.com.fiap.fipetracker.database.repository.FipeTrackerDbRepository
import br.com.fiap.fipetracker.database.repository.VeiculoRepository

@Composable
fun ListaVeiculoScreen(
    listaVeiculoScreenViewModel: ListaVeiculoScreenViewModel,
    navController: NavController
) {
    val listVeiculoSalvo by listaVeiculoScreenViewModel.listVeiculoSalvo.observeAsState(listOf())
    val veiculoRepository by listaVeiculoScreenViewModel.veiculoRepository.observeAsState(
        VeiculoRepository(LocalContext.current)
    )
    val fipeTrackerDbRepository by listaVeiculoScreenViewModel.fipeTrackerDbRepository.observeAsState(
        FipeTrackerDbRepository(LocalContext.current)
    )
    listaVeiculoScreenViewModel.onListVeiculoSalvoChanged(veiculoRepository.listaVeiculoSalvos())

    if (listVeiculoSalvo.isEmpty()) {
        Box {
            ErrorComponentFipeTracker(
                title = stringResource(id = R.string.lista_veiculo_screen_oops),
                subtitle = stringResource(id = R.string.lista_veiculo_screen_not_found),
                painter = painterResource(id = R.drawable.sadface),
                descriptionimage = stringResource(id = R.string.lista_veiculo_screen_sadfaceicon),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(300.dp, 300.dp),
                modifierbutton = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(50.dp)
                    .align(Alignment.BottomCenter),
                textbutton = stringResource(id = R.string.button_fipe_tracker_return),
                buttonChange = {
                    navController.popBackStack()
                }
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                IconButtonReturnFipeTracker(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp),
                    modifierIcon = Modifier.size(50.dp, 50.dp)
                )

                TextFipeTracker(
                    text = stringResource(id = R.string.lista_veiculo_screen_favorites),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(vertical = 10.dp),
                    color = colorResource(id = R.color.gray3)
                )

                IconButton(
                    onClick = {
                        fipeTrackerDbRepository.excluirTodasTabelas()
                        fipeTrackerDbRepository.limparPrimaryKeySequence()
                        listaVeiculoScreenViewModel.onListVeiculoSalvoChanged(listOf())
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = colorResource(id = R.color.blue_fipetracker)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.lista_veiculo_screen_delete_all),
                        modifier = Modifier.size(30.dp, 30.dp),
                        tint = colorResource(id = R.color.white)
                    )
                }

            }

            LazyColumn(
                modifier = Modifier.padding(vertical = 70.dp, horizontal = 20.dp),
                content = {
                    items(listVeiculoSalvo.reversed()) {
                        CardListaVeiculoScreenFipeTracker(
                            veiculo = it,
                            onClickButton = {
                                navController.navigate("veiculoscreen?codigofipe=${it.codigoFipe}&mesreferencia=${it.mesReferencia}&isloadingstatus=${false}&savedvehicle=${true}")
                            }
                        )
                    }
                })
        }
    }
}