package com.example.schoolhelper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoolhelper.R
import com.example.schoolhelper.ui.theme.Greenn
import com.example.schoolhelper.ui.theme.NumberBoxColor
import com.example.schoolhelper.ui.theme.SchoolHelperTheme
import com.example.schoolhelper.ui.theme.SpringGreen
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        TimerView(Modifier.fillMaxWidth(), LocalDateTime.of(2022, 7, 30, 12, 55))
    }
}


@Composable
fun TimerView(modifier: Modifier = Modifier, dateTo: LocalDateTime) {
    val now = LocalDateTime.now()
    val diff = dateTo.toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)

    val minutes = diff / 60 % 60
    val hours = diff / 60 / 60 % 24
    val days = diff / 60 / 60 / 24

    Box(
        modifier
            .clip(MaterialTheme.shapes.large)
            .background(Brush.horizontalGradient(listOf(SpringGreen, SpringGreen, Greenn)))
            .padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.counter_title))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.Center) {
                NumberBox(days / 10)
                NumberBox(days % 10)
                Text(text = ":", fontSize = 30.sp)
                NumberBox(hours / 10)
                NumberBox(hours % 10)
                Text(text = ":", fontSize = 30.sp)
                NumberBox(minutes / 10)
                NumberBox(minutes % 10)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = stringResource(R.string.days), style = MaterialTheme.typography.caption)
                Text(text = stringResource(R.string.hours), style = MaterialTheme.typography.caption)
                Text(text = stringResource(R.string.minutes), style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun NumberBox(number: Long) {
    Box(
        Modifier
            .padding(horizontal = 2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(NumberBoxColor)
            .padding(4.dp)
    ) {
        Text(text = number.toString(), fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolHelperTheme {
        TimerView(dateTo = LocalDateTime.now())
    }
}