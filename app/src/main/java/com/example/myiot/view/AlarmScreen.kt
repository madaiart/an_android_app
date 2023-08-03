package com.example.myiot.view

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myiot.model.Feed
import com.example.myiot.model.api.utils.NetworkResult
import com.example.myiot.view.utils.ThinkSpeakParser
import com.example.myiot.viewmodel.AlertsApiViewModel
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun AlarmScreen(vm: AlertsApiViewModel, paddingValues: PaddingValues, context: Context) {

    val resultData by vm.readResultData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 64.dp, end = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (resultData) {
            is NetworkResult.Initial -> {
                Text(text = "Initial")
            }

            is NetworkResult.Success -> {
                if (resultData.data != null) {
                    val numberLastEntry = resultData.data!!.channel.last_entry_id
                    val resultLastEntry =
                        resultData.data!!.feeds.find { feed -> feed.entry_id == numberLastEntry }
                            ?: Feed(
                                created_at = Date(),
                                entry_id = -1,
                                field1 = null,
                                field2 = null,
                                field3 = null,
                                field4 = null,
                                field5 = null,
                                field6 = null,
                                field7 = null,
                                field8 = null
                            );

                    val alertNames = ThinkSpeakParser.extractNames(resultData.data)
                    val tempState = remember {
                        mutableStateOf(
                            getAllValuesThinkSpeak(
                                resultLastEntry,
                                (alertNames.size).toShort()
                            )
                        )
                    }

                    if (alertNames.isNotEmpty()) {
//                        Toast.makeText(context, "New alerts", Toast.LENGTH_SHORT).show()
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.padding(top = 32.dp)) {
//                            AlarmCard()
                            alertNames.forEachIndexed { index, alertName ->
                                ToggleButton(alertName, tempState.value[index])
                                Spacer(modifier = Modifier.height(32.dp))
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            UpdadateAlertSaveButtons(vm, tempState)
                        }
                    }
                }
            }

            is NetworkResult.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResult.Error -> {
                Text(text = "Error: Por favor conectarse a internet")
            }
        }

    }
}

@Composable
fun UpdadateAlertSaveButtons(
    vm: AlertsApiViewModel,
    tempState: MutableState<List<MutableState<Boolean>>>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { vm.updateAlert() },
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.Gray
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Actualizar")
        }
        ClickableAlertButton(vm, tempState)
    }
}

@Composable
fun ClickableAlertButton(
    vm: AlertsApiViewModel,
    toggleState: MutableState<List<MutableState<Boolean>>>
) {
    var isButtonEnabled by remember { mutableStateOf(true) }
    var countdownSeconds by remember { mutableStateOf(15) }

    LaunchedEffect(isButtonEnabled) {
        if (!isButtonEnabled) {
            for (seconds in 15 downTo 1) {
                countdownSeconds = seconds
                delay(1000) // Delay for 1 second
            }
            isButtonEnabled = true // Enable the button after the countdown
        }
    }

    Button(
        onClick = {
            if (isButtonEnabled) {
                isButtonEnabled = false
                countdownSeconds = 15 // Reset the countdown when button is clicked
                vm.writeAlert(toggleState.value)
            }
        },
        enabled = isButtonEnabled, // Disable the button based on the isButtonEnabled state
        modifier = Modifier.padding(16.dp)
    ) {
        if (isButtonEnabled) {
            Text("Enviar")
        } else {
            Text("Enviar (${countdownSeconds}s)")
        }
    }
}

//@Composable
//fun AlarmCard() {
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Estimado precencia de gas",
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                    Icon(
////                        painter = painterResource(id = R.drawable.ic_temperature),
////                        contentDescription = null,
////                        tint = Color.Gray,
////                        modifier = Modifier.size(32.dp)
////                    )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = "10%", // Replace with your temperature value
//                    style = TextStyle(
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                )
//            }
//        }
//    }
//
//}

