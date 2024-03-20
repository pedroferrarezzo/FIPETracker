package br.com.fiap.fipetracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.fiap.fipetracker.R

@Composable
fun FooterFipeTracker(
    modifier: Modifier,
    onClickSearchButton: () -> Unit,
    onClickBookmarkButton: () -> Unit
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(colorResource(id = R.color.blue_fipetracker))
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = onClickSearchButton,
                    modifier = Modifier.size(40.dp, 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.searchicon),
                        contentDescription = stringResource(id = R.string.consulta_veiculo_screen_searchicon),
                        tint = colorResource(id = R.color.white)
                    )
                }
                TextFipeTracker(
                    text = stringResource(id = R.string.footer_search),
                    modifier = Modifier.absoluteOffset(y = (-10).dp),
                    color = colorResource(id = R.color.white)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = onClickBookmarkButton,
                    modifier = Modifier.size(40.dp, 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_regular),
                        contentDescription = stringResource(id = R.string.footer_bookmark_description),
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier.size(20.dp, 20.dp)
                    )
                }
                TextFipeTracker(
                    text = stringResource(id = R.string.footer_bookmark),
                    modifier = Modifier.absoluteOffset(y = (-10).dp),
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}
