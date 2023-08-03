package com.example.myiot.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myiot.model.api.utils.NetworkResult
import com.example.myiot.viewmodel.LightsApiViewModel


@Composable
fun SensorsScreen(
    vm: LightsApiViewModel,
    paddingValues: PaddingValues
) {
    val result by vm.readResultData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding(), start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(result){
            is NetworkResult.Initial ->{
                Text(text= "Initial")
            }
            is NetworkResult.Success -> {
                Text(text="${result?.data}")
            }
            is NetworkResult.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResult.Error -> {
                Text(text= "Error: ${result.message}")
            }
        }

    }
}