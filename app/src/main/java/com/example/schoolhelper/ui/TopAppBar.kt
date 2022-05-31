package com.example.schoolhelper.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    val iconSpace = 20.dp
    val title = buildAnnotatedString {
        append("Hi, ")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Mike")
            append("!")
        }
    }
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
            Icon(
                modifier = Modifier.padding(end = iconSpace),
                painter = painterResource(id = android.R.drawable.ic_menu_search),
                contentDescription = ""
            )
            Icon(
                modifier = Modifier.padding(end = iconSpace),
                painter = painterResource(id = android.R.drawable.ic_menu_preferences),
                contentDescription = ""
            )
            Icon(
                modifier = Modifier.padding(end = iconSpace),
                painter = painterResource(id = android.R.drawable.ic_menu_view),
                contentDescription = ""
            )
        })
}