package br.com.fiap.fipetracker.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import br.com.fiap.fipetracker.R

@Composable
fun IconButtonConditionFipeTracker(
    condition: Boolean,
    iconButtonOnClickTrue: () -> Unit,
    iconButtonPainterTrue: Painter,
    contentDescriptionIconTrue: String,
    modifier: Modifier,
    iconButtonOnClickFalse: () -> Unit,
    iconButtonPainterFalse: Painter,
    contentDescriptionIconFalse: String,
    modifierIconFalse: Modifier

) {
    if (condition) {
        IconButton(onClick = iconButtonOnClickTrue) {
            Icon(
                painter = iconButtonPainterTrue,
                contentDescription = contentDescriptionIconTrue,
                modifier = modifier,
                tint = colorResource(id = R.color.blue_fipetracker)
            )
        }
    } else {

        IconButton(onClick = iconButtonOnClickFalse) {
            Icon(
                painter = iconButtonPainterFalse,
                contentDescription = contentDescriptionIconFalse,
                modifier = modifierIconFalse,
                tint = colorResource(id = R.color.blue_fipetracker)
            )
        }
    }

}