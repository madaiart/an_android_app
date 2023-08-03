package com.example.myiot.view.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myiot.model.Feed
import com.example.myiot.model.HistoricData
import com.example.myiot.model.ReadDataResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

abstract class ThinkSpeakParser {
    companion object {
        fun extractNames(readData: ReadDataResponse?): List<String> {
            return listOfNotNull(
                readData?.channel?.field1,
                readData?.channel?.field2,
                readData?.channel?.field3,
                readData?.channel?.field4,
                readData?.channel?.field5,
                readData?.channel?.field6,
                readData?.channel?.field7,
                readData?.channel?.field8
            )
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getTodayHistoricDataByField(
            readData: ReadDataResponse,
            fieldName: String
        ): List<HistoricData> {
            val today = LocalDate.now()

            return readData.feeds
                .filter { getLocalDate(it.created_at).isEqual(today) } // To get just the today activity
                .sortedBy { it.entry_id }
                .map { HistoricData(getFieldByName(it, fieldName), it.created_at) }
        }

        fun booleanToInt(value: Boolean?): Int? {
            return when (value) {
                true -> 1
                false -> 0
                else -> null
            }
        }

        fun <T> readToBoolean(value: T):Boolean {
            return when (value) {
                is Double -> value == 1.0
                is String -> value == "1.0" || value == "1"
                is Number -> value.toDouble() == 1.0
                else -> false
            }
        }


        private fun getFieldByName(feed: Feed, fieldName: String): String {
            return when (fieldName) {
                "field1" -> feed.field1 ?: ""
                "field2" -> feed.field2 ?: ""
                "field3" -> feed.field3 ?: ""
                "field4" -> feed.field4 ?: ""
                "field5" -> feed.field5 ?: ""
                "field6" -> feed.field6 ?: ""
                "field7" -> feed.field7 ?: ""
                "field8" -> feed.field8 ?: ""

                else -> throw IllegalArgumentException("Invalid field name: $fieldName")
            }
        }



        @RequiresApi(Build.VERSION_CODES.O)
        private fun getLocalDate(date: Date): LocalDate {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate()
        }
    }

}