package com.example.myiot.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.myiot.model.WriteBody
import com.example.myiot.model.api.ThinkSpeakApiAlertRepo
import com.example.myiot.view.utils.ThinkSpeakParser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlertsApiViewModel @Inject constructor(private val repo: ThinkSpeakApiAlertRepo): ViewModel() {
    val readResultData = repo.readAlertsData

    private val updateFunction: (WriteBody) -> Unit = { body ->
        repo.updateWriteData(body)
    }
    private val common = Common(updateFunction)

    init {
        repo.getReadData()
    }
    fun writeAlert(updateWriteData: List<MutableState<Boolean>>){
        val booleanToIntConverter: (MutableState<Boolean>) -> Int? = { state ->
            ThinkSpeakParser.booleanToInt(state.value)
        }
        common.writeThinkSpeak(updateWriteData, booleanToIntConverter)
    }

    fun updateAlert(){
        repo.getReadData()
    }
}