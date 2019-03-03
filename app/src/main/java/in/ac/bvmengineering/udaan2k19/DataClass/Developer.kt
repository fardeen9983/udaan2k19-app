package `in`.ac.bvmengineering.udaan2k19.DataClass

import com.google.gson.annotations.SerializedName


class Developer {
    @SerializedName("name")
    val name: String? = null
    var type: String? = null
    @SerializedName("mail")
    val mail: String? = null
    @SerializedName("github")
    val github: String? = null
    @SerializedName("phone")
    val phone: String? = null
    var imageID: Int = 0
}