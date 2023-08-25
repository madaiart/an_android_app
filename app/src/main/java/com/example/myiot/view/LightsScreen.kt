package com.example.myiot.view

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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.myiot.viewmodel.LightsApiViewModel


@Composable
fun LightsScreen(
    vm: LightsApiViewModel,
    paddingValues: PaddingValues
) {
    val resultData by vm.readResultData.collectAsState()
    val resultNames by vm.readNames.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 64.dp, end = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        when (resultData) {
            is NetworkResult.Initial -> {
                Text(text = "Initial")
            }

            is NetworkResult.Success -> {
                if (resultData.data != null) {
                    val lights: List<Boolean> = listOf(
                        ThinkSpeakParser.readToBoolean(resultData.data?.field1),
                        ThinkSpeakParser.readToBoolean(resultData.data?.field2),
                        ThinkSpeakParser.readToBoolean(resultData.data?.field3),
                        ThinkSpeakParser.readToBoolean(resultData.data?.field4),
                        ThinkSpeakParser.readToBoolean(resultData.data?.field5)
                    )

                    val lightNames = ThinkSpeakParser.extractNames(resultNames.data)
                    val lightStates = lights.map { remember { mutableStateOf(it) } }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Column(modifier = Modifier.padding(top = 32.dp)) {
                            lightNames.forEachIndexed { index, lightName ->
                                Spacer(modifier = Modifier.height(32.dp))
                                LightToggleButton(lightName, lightStates[index])
                            }
                            // Add more LightToggleButton components for other lights
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { vm.updateLights() },
                                border = BorderStroke(1.dp, Color.Black),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    disabledContentColor = Color.Gray
                                ),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text("Actualizar")
                            }
                            ClickableLigthsButton(vm, lightStates)
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
fun LightToggleButton(lightName: String, lightState: MutableState<Boolean>) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = lightName

        )
        Switch(
            checked = lightState.value,
            onCheckedChange = { checked ->
                lightState.value = checked
                // Code to control the light based on the new state
            }
        )
    }
}

@Composable
fun ClickableLigthsButton(vm: LightsApiViewModel, lightState: List<MutableState<Boolean>>) {
    var isButtonEnabled by remember { mutableStateOf(true) }
    var countdownSeconds by remember { mutableStateOf(15) }

//    LaunchedEffect(isButtonEnabled) {
//        if (!isButtonEnabled) {
//            for (seconds in 15 downTo 1) {
//                countdownSeconds = seconds
//                delay(1000) // Delay for 1 second
//            }
//            isButtonEnabled = true // Enable the button after the countdown
//        }
//    }

    Button(
        onClick = {
//            if (isButtonEnabled) {
//                isButtonEnabled = false
//                countdownSeconds = 15 // Reset the countdown when button is clicked
            vm.writeLights(lightState)
//            }
        },
//        enabled = isButtonEnabled, // Disable the button based on the isButtonEnabled state
        modifier = Modifier.padding(16.dp)
    ) {
//        if (isButtonEnabled) {
        Text("Enviar")
//        } else {
//            Text("Enviar (${countdownSeconds}s)")
//        }
    }
}


fun getAllValuesThinkSpeak(data: Feed, quantity: Short): List<MutableState<Boolean>> {
    return (0..quantity - 1).map { i ->
        val fieldValue = getFieldByIndexThinkSpeak(data, i.toShort())
        mutableStateOf(fieldValue == "1")
    }
}


fun getFieldByIndexThinkSpeak(data: Feed, index: Short): String? {
    return when (index) {
        0.toShort() -> data.field1
        1.toShort() -> data.field2
        2.toShort() -> data.field3
        3.toShort() -> data.field4
        4.toShort() -> data.field5
        5.toShort() -> data.field6
        6.toShort() -> data.field7
        7.toShort() -> data.field8
        else -> null
    }
}