package br.com.fiap.fipetracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fipetracker.R

@Composable
fun <E> RowDropDownMenuFipeTracker(
    text: String,
    modifier: Modifier,
    onClickToExpand: () -> Unit,
    iconButtonModifier: Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    list: List<E>,
    onClickDropDownMenuItem: (Int) -> Unit,
    objectAttribute: (E) -> String,
    iconNotExpandedModifier: Modifier,
    iconExpandedModifier: Modifier,
    dropDownMenuOffset: DpOffset
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextFipeTracker(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier,
                color = colorResource(id = R.color.gray3)
            )
            IconButton(
                onClick = onClickToExpand,
                modifier = iconButtonModifier,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.blue_fipetracker)
                )
            ) {
                if (expanded) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = stringResource(id = R.string.veiculo_screen_play_icon),
                        modifier = iconExpandedModifier,
                        tint = colorResource(id = R.color.white)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(id = R.string.veiculo_screen_arrow_icon),
                        modifier = iconNotExpandedModifier,
                        tint = colorResource(id = R.color.white)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .background(colorResource(id = R.color.blue_fipetracker)),
                offset = dropDownMenuOffset
            ) {
                list.forEachIndexed { index, objeto ->
                    DropdownMenuItem(
                        onClick = { onClickDropDownMenuItem(index) },
                        text = {
                            TextFipeTracker(
                                text = objectAttribute(objeto),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.white)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                }
            }
        }
    }
}