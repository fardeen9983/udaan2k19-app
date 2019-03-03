package `in`.ac.bvmengineering.udaan2k19.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Manager : Serializable {
    @SerializedName("name")
    val name: String? = null
    @SerializedName("phone")
    val phone: String? = null

    override fun toString(): String {
        return "Manager{" +
                "name='" + name + '\''.toString() +
                ", phone='" + phone + '\''.toString() +
                '}'.toString()
    }
}