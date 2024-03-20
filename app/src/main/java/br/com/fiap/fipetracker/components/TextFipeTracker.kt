package br.com.fiap.fipetracker.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import br.com.fiap.fipetracker.ui.theme.UbuntuCondensed

@Composable
fun TextFipeTracker(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null
) {

    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        modifier = modifier,
        textAlign = textAlign,
        fontFamily = UbuntuCondensed
    )

}