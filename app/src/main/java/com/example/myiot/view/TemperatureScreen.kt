package com.example.myiot.view

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myiot.model.api.utils.NetworkResult
import com.example.myiot.view.utils.ThinkSpeakParser
import com.example.myiot.viewmodel.ThermostatApiViewModel

@Composable
fun TemperatureScreen(vm: ThermostatApiViewModel, paddingValues: PaddingValues) {
    val resultData by vm.readResultData.collectAsState()
    val resultDataSignal by vm.readResultDataSignal.collectAsState()
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
                    val currentTemperature = resultDataSignal.data?.field1?.toFloatOrNull()
                        ?.let { "%.1f".format(it).toFloat() }

                    val switchThermostat = ThinkSpeakParser.readToBoolean(resultData.data?.field1)
                    val switchGLP = ThinkSpeakParser.readToBoolean(resultData.data?.field2)
                    val switchForce = ThinkSpeakParser.readToBoolean(resultData.data?.field3)

                    val tempState = remember { mutableStateOf(switchThermostat) }
                    val glpState = remember { mutableStateOf(switchGLP) }
                    val forceState = remember { mutableStateOf(switchForce) }

                    val tempNames = ThinkSpeakParser.extractNames(resultNames.data)
                    Log.d("TempNames", "$tempNames")

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(modifier = Modifier.padding(top = 32.dp)) {
                            Spacer(modifier = Modifier.height(32.dp))
                            TemperatureCard(currentTemperature)
                            Spacer(modifier = Modifier.height(64.dp))
                            ToggleButton(tempNames[0], tempState)
                            Spacer(modifier = Modifier.height(32.dp))
                            ToggleButton(tempNames[2], forceState)
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            UpdadateThermostatSaveButtons(
                                vm,
                                listOf(tempState, glpState, forceState)
                            )
                        }
                    }
                }
            }

            is NetworkResult.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResult.Error -> {
                Text(text = "Error:  Por favor conectarse a internet")
            }
        }

    }

}


@Composable
fun TemperatureCard(currentTemperature: Float?) {

    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Temperature",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_temperature),
//                        contentDescription = null,
//                        tint = Color.Gray,
//                        modifier = Modifier.size(32.dp)
//                    )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${currentTemperature ?: "~ "} °C", // Replace with your temperature value
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

}


@Composable
fun ToggleButton(toggleName: String, toggleState: MutableState<Boolean>, enabled: Boolean = true) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = toggleName
        )
        Switch(
            enabled = enabled,
            checked = toggleState.value,
            onCheckedChange = { checked ->
                toggleState.value = checked
                // Code to control the light based on the new state
            }
        )
    }
}

@Composable
fun UpdadateThermostatSaveButtons(
    vm: ThermostatApiViewModel,
    tempState: List<MutableState<Boolean>>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { vm.updateThermostatGLP() },
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.Gray
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Actualizar")
        }
        ClickableThermostatButton(vm, tempState)
    }
}

@Composable
fun ClickableThermostatButton(
    vm: ThermostatApiViewModel,
    toggleState: List<MutableState<Boolean>>
) {
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
            vm.writeThermostatGLP(toggleState)
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

