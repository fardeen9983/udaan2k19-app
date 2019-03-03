package `in`.ac.bvmengineering.udaan2k19.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Participant : Serializable {
    @SerializedName("name")
    var name: String? = null
    var phone: String? = null
    var events: ArrayList<Event>? = null
    var certificates: ArrayList<String>? = null
    var token: String? = null

}