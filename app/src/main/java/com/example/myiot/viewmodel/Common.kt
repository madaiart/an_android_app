package com.example.myiot.viewmodel

import com.example.myiot.model.WriteBody

class Common(private val updateFunction: (WriteBody) -> Unit) {

    fun <T> writeThinkSpeak(
        updateWriteData: List<T>,
        booleanToIntConverter: (T) -> Int?
    ) {
        val body = WriteBody(
            api_key = "",
            created_at = null,//Date(),
            field1 = if (updateWriteData.indices.contains(0)) booleanToIntConverter(updateWriteData[0]) else null,
            field2 = if (updateWriteData.indices.contains(1)) booleanToIntConverter(updateWriteData[1]) else null,
            field3 = if (updateWriteData.indices.contains(2)) booleanToIntConverter(updateWriteData[2]) else null,
            field4 = if (updateWriteData.indices.contains(3)) booleanToIntConverter(updateWriteData[3]) else null,
            field5 = if (updateWriteData.indices.contains(4)) booleanToIntConverter(updateWriteData[4]) else null,
            field6 = if (updateWriteData.indices.contains(5)) booleanToIntConverter(updateWriteData[5]) else null,
            field7 = if (updateWriteData.indices.contains(6)) booleanToIntConverter(updateWriteData[6]) else null,
            field8 = if (updateWriteData.indices.contains(7)) booleanToIntConverter(updateWriteData[7]) else null,
            longitude = null,
            latitude = null,
            status = "app"
        )
        updateFunction(body)
    }
}
