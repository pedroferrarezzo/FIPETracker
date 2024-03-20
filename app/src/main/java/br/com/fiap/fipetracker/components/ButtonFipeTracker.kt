package br.com.fiap.fipetracker.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.fiap.fipetracker.R

@Composable
fun ButtonFipeTracker(
    text: String,
    onValueChange: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onValueChange,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = colorResource(id = R.color.blue_fipetracker)
            ),
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(6.dp)

    ) {
        TextFipeTracker(
            text = text,
            color = colorResource(id = R.color.white)
        )
    }
}


