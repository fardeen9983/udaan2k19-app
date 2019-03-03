package `in`.ac.bvmengineering.udaan2k19.DataClass

import java.io.Serializable
import java.util.*


class EventCategory(val image: Int, var name: String?) : Serializable {
    val events: ArrayList<Event> = ArrayList()
    val managers: ArrayList<Manager> = ArrayList()

}
