package com.example.myiot.model

import java.util.Date

data class WriteResponse(
     val channel_id: Int,
     val created_at: Date,
     val entry_id: Int,
     val field1: Any?,
     val field2: Any?,
     val field3: Any?,
     val field4: Any?,
     val field5: Any?,
     val field6: Any?,
     val field7: Any?,
     val field8: Any?,
     val latitude: Any?,
     val longitude: Any?,
     val elevation: Any?,
     val status: Any?
)

data class WriteBody(
     var api_key: String,
     val created_at: Date?,
     val field1: Any?,
     val field2: Any?,
     val field3: Any?,
     val field4: Any?,
     val field5: Any?,
     val field6: Any?,
     val field7: Any?,
     val field8: Any?,
     val latitude: Any?,
     val longitude: Any?,
     val status: Any?
)