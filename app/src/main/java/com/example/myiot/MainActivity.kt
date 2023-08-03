package com.example.myiot

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.app.NotificationCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myiot.ui.theme.MyIOTTheme
import com.example.myiot.ui.theme.PurpleGrey80
import com.example.myiot.view.AlarmScreen
import com.example.myiot.view.GLPScreen
import com.example.myiot.view.LightsScreen
import com.example.myiot.view.SensorsBottomNav
import com.example.myiot.view.SensorsScreen
import com.example.myiot.view.TemperatureScreen
import com.example.myiot.viewmodel.AlertsApiViewModel
import com.example.myiot.viewmodel.GLPApiViewModel
import com.example.myiot.viewmodel.LightsApiViewModel
import com.example.myiot.viewmodel.ThermostatApiViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String) {
    object Sensors : Destination("sensors")
    object GLP : Destination("glp")
    object Lights : Destination("lights")
    object Temperature : Destination("temperature")
    object Alarm : Destination("alarm")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val lvm by viewModels<LightsApiViewModel>()
    private val gvm by viewModels<GLPApiViewModel>()
    private val tvm by viewModels<ThermostatApiViewModel>()
    private val avm by viewModels<AlertsApiViewModel>()

    private val CHANNEL_ID = "my_channel_id"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyIOTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    SensorsScaffold(
                        navController = navController, lvm = lvm, gvm = gvm, avm = avm, tvm = tvm,
                        context = this@MainActivity
                    )
                }
            }
        }
        // Create the notification channel
        createNotificationChannel()

        // Build and display the complex notification
        showComplexNotification()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Notification Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showComplexNotification() {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.fire)
            .setContentTitle("My IOT")
            .setContentText("Sistema Seguro")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Sistema Seguro: No alertas registradas")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorsScaffold(
    navController: NavHostController,
    lvm: LightsApiViewModel,
    tvm: ThermostatApiViewModel,
    gvm: GLPApiViewModel,
    avm: AlertsApiViewModel,
    context: Context
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "MyIOT - Sensors",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = PurpleGrey80)

            )

        },
        bottomBar = { SensorsBottomNav(navController = navController) },
        content = { contentPadding ->
            Box(
                modifier = Modifier.padding(contentPadding),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Lights.route
                ) {
                    composable(Destination.Sensors.route) {
                        SensorsScreen(lvm, contentPadding)
                    }
                    composable(Destination.Lights.route) {
                        LightsScreen(lvm, contentPadding)
                    }
                    composable(Destination.Temperature.route) {
                        TemperatureScreen(tvm, contentPadding)
                    }
                    composable(Destination.GLP.route) {
                        GLPScreen(gvm, contentPadding)
                    }
                    composable(Destination.Alarm.route) {
                        AlarmScreen(avm, contentPadding, context)
                    }
                }

            }
        }
    )
}

