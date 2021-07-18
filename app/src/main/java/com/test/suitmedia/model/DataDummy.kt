package com.test.suitmedia.model

import com.test.suitmedia.R

object DataDummy {
    fun generateDummyEvent() : List<EventModel> {
        val event = ArrayList<EventModel>()

        event.add(
            EventModel(
            "BINUS Virtual Job Expo",
            "21 - 27 Juni 2021",
            R.drawable.event5
        ))
        event.add(
            EventModel(
            "ICOMITEE 2021",
            "27 - 28 Okt 2021",
            R.drawable.event2
        ))
        event.add(
            EventModel(
            "International Youth Conference 6.0",
            "05 Mar 2021 - 08 Agu 2021",
            R.drawable.event3
        ))
        event.add(
            EventModel(
            "TOEFL AKBAR ONLINE",
            "19 - 20 Juni 2021",
            R.drawable.event4
        ))
        event.add(
            EventModel(
            "TOMPS BLOG COMPETITION",
            "14 Jun 2021 - 01 Agu 2021",
            R.drawable.event1
        ))
        return event
    }
}