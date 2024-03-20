package br.com.fiap.fipetracker.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fipetracker.R

@Composable
fun ErrorComponentFipeTracker(
    title: String,
    subtitle: String,
    painter: Painter,
    descriptionimage: String,
    modifier: Modifier,
    modifierbutton: Modifier,
    textbutton: String,
    buttonChange: () -> Unit

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 10.dp, vertical = 30.dp)
        ) {
            TextFipeTracker(
                text = title,
                fontSize = 50.sp,
                color = colorResource(id = R.color.gray3),
                fontWeight = FontWeight.Bold
            )
            TextFipeTracker(
                text = subtitle,
                fontSize = 30.sp,
                color = colorResource(id = R.color.gray3),
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            )
        }

        Image(
            painter = painter,
            contentDescription = descriptionimage,
            modifier = modifier
        )

        ButtonFipeTracker(
            text = textbutton,
            onValueChange = buttonChange,
            modifier = modifierbutton
        )
    }

}