package br.com.fiap.fipetracker.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.components.CardVeiculoScreenFipeTracker
import br.com.fiap.fipetracker.components.ErrorComponentFipeTracker
import br.com.fiap.fipetracker.components.IconButtonConditionFipeTracker
import br.com.fiap.fipetracker.components.IconButtonReturnFipeTracker
import br.com.fiap.fipetracker.components.RowDropDownMenuFipeTracker
import br.com.fiap.fipetracker.components.TextFipeTracker
import br.com.fiap.fipetracker.database.repository.VeiculoRepository
import br.com.fiap.fipetracker.functions.callBrasilAPIFIPE
import br.com.fiap.fipetracker.functions.toastMessageFipeTracker
import br.com.fiap.fipetracker.functions.webScrappingImageVehicle
import br.com.fiap.fipetracker.model.CodigoFipeAndMesReferencia
import coil.compose.AsyncImage

@Composable
fun VeiculoScreen(
    veiculoScreenViewModel: VeiculoScreenViewModel,
    navController: NavController,
    codigoFipe: String,
    isLoadingStatus: Boolean,
    mesReferencia: String,
    isConnected: Boolean,
    savedVehicle: Boolean
) {
    /* Nem todas as variaveis de estado puderam ser gerenciadas com o LiveData, em decorrencia do uso de corotinas.
       Quando alteradas dentro do "Coroutine Scope" fornecido pelo Composable LaunchedEffect, as variaveis
       observadoras construidas por meio do metodo "observeAsState" nao reagiam as mudanças.
       Questão relatada ao tutor*/

    // ESTADO NÃO GERENCIADO
    val context = LocalContext.current
    val veiculoRepository = VeiculoRepository(context)

    // GERENCIAMENTO DE ESTADO COM LIVEDATA
    val scope by veiculoScreenViewModel.scope.observeAsState(rememberCoroutineScope())
    val expandedDropDownMenu by veiculoScreenViewModel.expandedDropdownMenu.observeAsState(false)
    val codigoFipeAndMesReferencia by veiculoScreenViewModel.codigoFipeAndMesReferencia.observeAsState(
        CodigoFipeAndMesReferencia()
    )
    val indexListVeiculoFipe by veiculoScreenViewModel.indexListVeiculoFipe.observeAsState(0)

    // GERENCIAMENTO DE ESTADO - STATE OF
    var isSavedVehicle by remember {
        mutableStateOf(savedVehicle)
    }
    var isLoading by remember {
        mutableStateOf(isLoadingStatus)
    }
    var listVeiculoFipe by remember {
        mutableStateOf(
            veiculoRepository.listaVeiculoByFipeAndMesReferencia(
                codigoFipe = codigoFipe,
                mesReferencia = mesReferencia
            )
        )
    }
    var isConnectedStatus by remember {
        mutableStateOf(isConnected)
    }

    if (isLoading) {

        BackHandler {
            toastMessageFipeTracker(
                context = context,
                text = context.getString(R.string.veiculo_screen_toast_message)
            )
        }

        LaunchedEffect(key1 = Unit) {

            try {
                listVeiculoFipe =
                    callBrasilAPIFIPE(codigoFipe).filter { veiculo -> veiculo.anoModelo != 32000L }
                if (listVeiculoFipe.isNotEmpty()) {
                    webScrappingImageVehicle(
                        listVeiculos = listVeiculoFipe,
                        scope = scope
                    )
                    isSavedVehicle = veiculoRepository.verificaVeiculoDb(
                        listVeiculoFipe[0].codigoFipe,
                        listVeiculoFipe[0].mesReferencia
                    )
                    isLoading = false
                }
            } catch (t: Throwable) {
                when (t.message) {
                    "INCORRECT_FIPE_CODE" -> {
                        listVeiculoFipe = listOf()
                        isLoading = false
                    }

                    "API_PROBLEM" -> {
                        isConnectedStatus = false
                        isLoading = false
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp, 100.dp),
                color = colorResource(id = R.color.blue_fipetracker)
            )
        }

    } else {

        if (!isConnectedStatus) {
            Box {
                ErrorComponentFipeTracker(
                    title = stringResource(id = R.string.veiculo_screen_oops),
                    subtitle = stringResource(id = R.string.veiculo_screen_verify_internet),
                    painter = painterResource(id = R.drawable.nointernet),
                    descriptionimage = stringResource(id = R.string.veiculo_screen_no_internet_image),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(400.dp, 400.dp)
                        .padding(horizontal = 10.dp)
                        .offset(y = 20.dp),
                    modifierbutton = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .height(50.dp)
                        .align(Alignment.BottomCenter),
                    textbutton = stringResource(id = R.string.button_fipe_tracker_return),
                    buttonChange = {
                        navController.popBackStack()
                    })
            }
        } else if (listVeiculoFipe.isEmpty()) {
            Box {
                ErrorComponentFipeTracker(
                    title = stringResource(id = R.string.veiculo_screen_oops),
                    subtitle = stringResource(id = R.string.veiculo_screen_not_found),
                    painter = painterResource(id = R.drawable.question),
                    descriptionimage = stringResource(id = R.string.veiculo_screen_question),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(600.dp, 600.dp),
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

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                IconButtonReturnFipeTracker(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(5.dp),
                    modifierIcon = Modifier.size(50.dp, 50.dp)
                )

                TextFipeTracker(
                    text = listVeiculoFipe[indexListVeiculoFipe].modelo,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                        .offset(y = (-5).dp),
                    color = colorResource(id = R.color.gray3)
                )

                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-5).dp)
                ) {
                    AsyncImage(
                        model = listVeiculoFipe[indexListVeiculoFipe].urlImagemMarca,
                        contentDescription = stringResource(id = R.string.veiculo_screen_manufacturer_image),
                        modifier = Modifier
                            .size(40.dp, 40.dp)
                            .offset(x = 10.dp)
                            .shadow(6.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds
                    )

                    IconButtonConditionFipeTracker(
                        condition = isSavedVehicle,
                        iconButtonOnClickTrue = {
                            veiculoScreenViewModel.setCodigoFipeAndMesReferencia(
                                listVeiculoFipe[indexListVeiculoFipe].codigoFipe,
                                listVeiculoFipe[indexListVeiculoFipe].mesReferencia
                            )
                            veiculoRepository.excluirVeiculo(codigoFipeAndMesReferencia)
                            toastMessageFipeTracker(
                                context = context,
                                text = context.getString(R.string.veiculo_screen_consult_delete)
                            )
                            isSavedVehicle = false
                        },
                        iconButtonPainterTrue = painterResource(id = R.drawable.bookmark_solid),
                        contentDescriptionIconTrue = stringResource(id = R.string.veiculo_screen_bookmark_solid),
                        modifier = Modifier.size(30.dp, 30.dp),
                        iconButtonOnClickFalse = {
                            for (veiculo in listVeiculoFipe) {
                                veiculoRepository.criarVeiculo(veiculo)
                            }
                            toastMessageFipeTracker(
                                context = context,
                                text = context.getString(R.string.veiculo_screen_consult_saved)
                            )
                            isSavedVehicle = true
                        },
                        iconButtonPainterFalse = painterResource(id = R.drawable.bookmark_regular),
                        contentDescriptionIconFalse = stringResource(id = R.string.veiculo_screen_bookmark_regular),
                        modifierIconFalse = Modifier.size(30.dp, 30.dp)
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = listVeiculoFipe[indexListVeiculoFipe].urlImagemModelo,
                        contentDescription = stringResource(id = R.string.veiculo_screen_model_image),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .padding(horizontal = 30.dp)
                            .shadow(6.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }

                RowDropDownMenuFipeTracker(
                    text = stringResource(id = R.string.veiculo_screen_specification),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .offset(y = 5.dp),
                    iconButtonModifier = Modifier
                        .size(25.dp, 25.dp)
                        .absoluteOffset(x = (-5).dp, y = 5.dp),
                    onClickToExpand = { veiculoScreenViewModel.onExpandedDropdownMenu(true) },
                    expanded = expandedDropDownMenu,
                    onDismissRequest = { veiculoScreenViewModel.onExpandedDropdownMenu(false) },
                    list = listVeiculoFipe,
                    onClickDropDownMenuItem = {
                        veiculoScreenViewModel.onIndexListVeiculoFipeChanged(it)
                        veiculoScreenViewModel.onExpandedDropdownMenu(false)
                    },
                    objectAttribute = {
                        it.anoModelo.toString()
                    },
                    iconExpandedModifier = Modifier.size(25.dp, 25.dp),
                    iconNotExpandedModifier = Modifier.size(50.dp, 50.dp),
                    dropDownMenuOffset = DpOffset(x = 230.dp, y = (-100).dp)
                )

                CardVeiculoScreenFipeTracker(
                    listVeiculoFipe,
                    indexListVeiculoFipe
                )
            }
        }
    }
}