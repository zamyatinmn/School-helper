package com.example.schoolhelper.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoolhelper.R
import com.example.schoolhelper.ui.theme.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        TimerView(Modifier.fillMaxWidth(), LocalDateTime.of(2022, 7, 30, 12, 55))
        Spacer(modifier = Modifier.height(20.dp))
        ClassesView(
            classes = listOf(
                SchoolClass(
                    "History",
                    "Mrs. Thomas",
                    android.R.drawable.ic_menu_compass,
                    "",
                    LocalTime.now(),
                    "urlurlurl"
                ),
                SchoolClass(
                    "Literature",
                    "Mrs. Barros",
                    android.R.drawable.button_onoff_indicator_off,
                    "",
                    LocalTime.now(),
                    ""
                ),
                SchoolClass(
                    "Physical Education",
                    "Mrs. Barros",
                    android.R.drawable.stat_notify_chat,
                    "Intensive preparation for The Junior World Championship in Los Angeles.",
                    LocalTime.now(),
                    ""
                ),
                SchoolClass(
                    "Math",
                    "Mr. Johny",
                    android.R.drawable.ic_media_play,
                    "",
                    LocalTime.now(),
                    ""
                )
            )
        )
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
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
                Text(
                    text = stringResource(R.string.hours),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = stringResource(R.string.minutes),
                    style = MaterialTheme.typography.caption
                )
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
            .padding(8.dp)
    ) {
        Text(text = number.toString(), fontSize = 30.sp)
    }
}

@Composable
fun ClassesView(modifier: Modifier = Modifier, classes: List<SchoolClass>) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

//    SideEffect {
//        scope.launch {
//            scrollState.scrollToItem(1)
//        }
//    }

    Column(
        modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = stringResource(R.string.classes_title))
            Text(
                text = "${classes.size} classes today",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            state = scrollState
        ) {
            items(classes) { item ->
                ClassView(item = item)
            }
        }
    }
}

data class SchoolClass(
    val name: String,
    val teacher: String,
    val iconResource: Int,
    val description: String,
    val time: LocalTime,
    val videoStream: String
)

@Composable
fun ClassView(item: SchoolClass) {
    Card(
        modifier = Modifier
            .width(
                with(LocalDensity.current) {
                    LocalView.current.width.toDp() - 60.dp
                })
            .height(100.dp),
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = item.iconResource),
                    contentDescription = item.name
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = item.name)
                    Text(text = item.teacher)
                }
            }
            if (item.videoStream.isNotEmpty()) {
                Box(
                    Modifier
                        .background(Bluee)
                        .fillMaxHeight()
                        .weight(0.2f)
                        .clickable {

                        }
                ) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .rotate(90f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(textAlign = TextAlign.Center, text = "Open in ")
                        Icon(
                            modifier = Modifier.padding(start = 4.dp, top = 4.dp),
                            painter = painterResource(id = android.R.drawable.presence_video_online),
                            contentDescription = "Open video"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolHelperTheme {
        TimerView(dateTo = LocalDateTime.now())
    }
}