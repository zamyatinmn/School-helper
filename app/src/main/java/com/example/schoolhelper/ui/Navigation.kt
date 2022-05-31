package com.example.schoolhelper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schoolhelper.ui.theme.NavBackground
import com.example.schoolhelper.ui.theme.SpringGreen


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.HOME,
        BottomNavItem.CLASSES,
        BottomNavItem.LIST,
        BottomNavItem.FAVORITE
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            BottomBarItem(item, currentRoute == item.screenRoute) {
                navController.navigate(item.screenRoute) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

@Composable
fun RowScope.BottomBarItem(item: BottomNavItem, selected: Boolean, onClickAction: () -> Unit) {
    val color = if (selected) SpringGreen else MaterialTheme.colors.onSurface
    Box(
        modifier = Modifier
            .selectable(
                selected = selected,
                onClick = onClickAction,
                role = Role.Tab,
            )
            .clip(MaterialTheme.shapes.medium)
            .background(if (selected) NavBackground else Color.Transparent)
            .weight(1f)
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = item.iconId),
                tint = color,
                contentDescription = item.label
            )
            if (selected) Text(
                modifier = Modifier.padding(start = 6.dp),
                text = item.label,
                color = color,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.HOME.screenRoute) {
        composable(BottomNavItem.HOME.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.CLASSES.screenRoute) {
            ClassesScreen()
        }
        composable(BottomNavItem.LIST.screenRoute) {
            ListScreen()
        }
        composable(BottomNavItem.FAVORITE.screenRoute) {
            FavoriteScreen()
        }
    }
}

enum class BottomNavItem(val label: String, val iconId: Int, val screenRoute: String) {
    HOME("Home", android.R.drawable.ic_menu_gallery, "home"),
    CLASSES("Classes", android.R.drawable.ic_menu_my_calendar, "classes"),
    LIST("List", android.R.drawable.ic_menu_compass, "list"),
    FAVORITE("Favorite", android.R.drawable.star_off, "favorite"),
}