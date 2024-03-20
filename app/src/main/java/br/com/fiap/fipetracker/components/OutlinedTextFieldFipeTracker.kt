package br.com.fiap.fipetracker.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.ui.theme.UbuntuCondensed

@Composable
fun OutlinedTextFieldFipeTracker(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier,
    trailingIcon: @Composable() (() -> Unit)?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            TextFipeTracker(text = placeholder)
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.white1),
            focusedContainerColor = colorResource(id = R.color.white),
            unfocusedBorderColor = colorResource(id = R.color.gray1),
            focusedBorderColor = colorResource(id = R.color.gray2),
            unfocusedPlaceholderColor = colorResource(id = R.color.gray1),
            focusedPlaceholderColor = colorResource(id = R.color.gray3),
            focusedTextColor = colorResource(id = R.color.gray3),
            unfocusedTextColor = colorResource(id = R.color.gray1),
            errorBorderColor = colorResource(id = R.color.red_error),
            errorCursorColor = colorResource(id = R.color.red_error),
            errorPlaceholderColor = colorResource(id = R.color.red_error),
            errorTextColor = colorResource(id = R.color.red_error),
            errorTrailingIconColor = colorResource(id = R.color.red_error),
            focusedTrailingIconColor = colorResource(id = R.color.blue_fipetracker),
            unfocusedTrailingIconColor = colorResource(id = R.color.blue_fipetracker)
        ),
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        textStyle = TextStyle(fontFamily = UbuntuCondensed),
        trailingIcon = trailingIcon
    )
}
