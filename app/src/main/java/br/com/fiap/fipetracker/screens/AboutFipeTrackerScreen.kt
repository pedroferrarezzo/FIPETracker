package br.com.fiap.fipetracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.fipetracker.R
import br.com.fiap.fipetracker.components.ButtonFipeTracker
import br.com.fiap.fipetracker.components.TextFipeTracker

@Composable
fun AboutFipeTrackerScreen(
    navController: NavController
) {

    Box {
        Image(
            painter = painterResource(id = R.drawable.fipetracker),
            contentDescription = stringResource(id = R.string.about_fipe_tracker_screen_descriptionlogo),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(300.dp, 300.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 10.dp, vertical = 50.dp)
                .offset(y = 20.dp)
        ) {

            TextFipeTracker(
                text = stringResource(id = R.string.about_fipe_tracker_screen_welcome),
                fontSize = 50.sp,
                color = colorResource(id = R.color.gray3),
                fontWeight = FontWeight.Bold
            )

            TextFipeTracker(
                text = stringResource(id = R.string.about_fipe_tracker_screen_explanation),
                fontSize = 20.sp,
                color = colorResource(id = R.color.gray3),
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.brasilapi),
            contentDescription = stringResource(id = R.string.about_fipe_tracker_screen_descriptionapi),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(130.dp, 40.dp)
                .offset(y = (-80).dp)
                .shadow(3.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds
        )

        ButtonFipeTracker(
            text = stringResource(id = R.string.button_fipe_tracker_return),
            onValueChange = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter)
        )
    }
}