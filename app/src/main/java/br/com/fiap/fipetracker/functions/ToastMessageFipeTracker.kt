package br.com.fiap.fipetracker.functions

import android.content.Context
import android.widget.Toast


fun toastMessageFipeTracker(
    context: Context,
    text: String
) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}