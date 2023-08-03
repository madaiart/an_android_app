package com.example.myiot.model

import java.util.Date

data class ReadDataResponse(
 val channel : Channel,
 val feeds: List<Feed>,
)

data class ReadFieldResponse(
    val channel : Channel,
    val feeds: List<Feed>,
)

data class ReadStatusResponse(
    val name: String,
    val latitude: String?,
    val longitude: String?,
    val feeds: List<Feed>,
)

data class ReadLastEntryResponse(
     val created_at: Date,
     val entry_id: Int,
     val field1: String?,
     val field2: String?,
     val field3: String?,
     val field4: String?,
     val field5: String?,
     val field6: String?,
     val field7: String?,
     val field8: String?
) 

data class ReadLastFieldEntryResponse(
     val created_at: Date,
     val entry_id: Int,
     val field1: String?,
     val field2: String?,
     val field3: String?,
     val field4: String?,
     val field5: String?,
     val field6: String?,
     val field7: String?,
     val field8: String?
) 

data class ReadLastStatusResponse(
    val created_at: Date,
    val entry_id: Int,
    val status: String?
)

data class Channel(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: String?,
    val longitude: String?,
    val field1: String?,
    val field2: String?,
    val field3: String?,
    val field4: String?,
    val field5: String?,
    val field6: String?,
    val field7: String?,
    val field8: String?,
    val created_at: Date,
    val updated_at: Date,
    val last_entry_id: Int,
)

data class Feed(
    val created_at: Date,
    val entry_id: Int,
    val field1: String?,
    val field2: String?,
    val field3: String?,
    val field4: String?,
    val field5: String?,
    val field6: String?,
    val field7: String?,
    val field8: String?,
)

data class HistoricData(
    val field: String?, val time: Date
)
