package com.example.schoolhelper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolhelper.ui.theme.Greenn
import com.example.schoolhelper.ui.theme.NumberBoxColor
import com.example.schoolhelper.ui.theme.SchoolHelperTheme
import com.example.schoolhelper.ui.theme.SpringGreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        TimerView(Modifier.fillMaxWidth())
    }
}


@Composable
fun TimerView(modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(MaterialTheme.shapes.large)
            .background(Brush.horizontalGradient(listOf(SpringGreen, SpringGreen, Greenn)))
            .padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Are you ready for exams?")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                NumberBox(0)
                NumberBox(0)
                Text(text = ":")
                NumberBox(0)
                NumberBox(0)
                Text(text = ":")
                NumberBox(0)
                NumberBox(0)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Days", style = MaterialTheme.typography.caption)
                Text(text = "Hours", style = MaterialTheme.typography.caption)
                Text(text = "Minutes", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun NumberBox(number: Int) {
    Box(
        Modifier
            .padding(horizontal = 2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(NumberBoxColor)
            .padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolHelperTheme {
        TimerView()
    }
}