package com.example.myiot.view

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myiot.Destination
import com.example.myiot.R

@Composable
fun SensorsBottomNav(navController: NavController) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    val iconFire = painterResource(id = R.drawable.fire)
    val iconLight = painterResource(id = R.drawable.lightbulb)
    val iconNotification = painterResource(id = R.drawable.notifications)
    val iconThermostat = painterResource(id = R.drawable.thermostat)

    val items = listOf("Luces", "Calentador", "GLP", "Alerta")
    val icons = listOf(iconLight, iconThermostat, iconFire, iconNotification)
    val destinations = listOf(
        Destination.Lights.route,
        Destination.Temperature.route,
        Destination.GLP.route,
        Destination.Alarm.route,
    )

    NavigationBar {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = currentDestination?.route === destinations[index],
                onClick = {
                    navController.navigate(destinations[index]) {
                        popUpTo(destinations[index])
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}