package `in`.ac.bvmengineering.udaan2k19.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class Event : Serializable {

    @SerializedName("tagline")
    val tagLine: String? = null
    @SerializedName("entryFee")
    val fees: Int = 0
    @SerializedName("notes")
    val notes: String? = null
    @SerializedName("_id")
    val id: String? = null
    @SerializedName("department")
    val dept: String? = null
    @SerializedName("eventName")
    val name: String? = null
    @SerializedName("eventType")
    val type: String? = null
    @SerializedName("rounds")
    val rounds: ArrayList<String>? = null
    @SerializedName("managers")
    val managers: ArrayList<Manager>? = null
    @SerializedName("teamSize")
    val teamSize: Int = 0

    override fun toString(): String {
        return "Event{" +
                "tagLine='" + tagLine + '\''.toString() +
                ", fees=" + fees +
                ", id='" + id + '\''.toString() +
                ", dept='" + dept + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", rounds=" + rounds +
                ", managers=" + managers +
                '}'.toString()
    }

}
