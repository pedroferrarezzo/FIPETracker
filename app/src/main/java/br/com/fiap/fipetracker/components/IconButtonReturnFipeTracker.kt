package br.com.fiap.fipetracker.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import br.com.fiap.fipetracker.R

@Composable
fun IconButtonReturnFipeTracker(
    onClick: () -> Unit,
    modifier: Modifier,
    modifierIcon: Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = colorResource(id = R.color.blue_fipetracker)
        ),
        modifier = modifier

    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.icon_button_fipe_tracker_return_icon),
            modifier = modifierIcon,
            tint = colorResource(id = R.color.white)
        )
    }

}