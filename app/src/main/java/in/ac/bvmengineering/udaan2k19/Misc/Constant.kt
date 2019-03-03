package `in`.ac.bvmengineering.udaan2k19.Misc

import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class Constant {

    companion object {
        const val AUTOMOTIVE_PHILOSOPHER = "mechProd"
        const val BUILDER_OF_AZKABAN = "civil"
        const val CHAMBER_OF_CODERS = "cpit"
        const val HALF_WAVE_PRINCE = "ecel"
        const val ORDER_OF_OHMS = "ee"
        const val GOBLET_OF_WORKSHOPS = "workshop"
        const val SCAMANDERS_SUITCASE = "non-tech"
        const val MAD_HOLLOWS = "cultural"
        const val ANDROID_DEVELOPER = "Android Developer"
        const val DEVELOPER_HEAD = "Developer Head"
        const val WEB_DEVELOPER = "Web Developer"
        const val UI_DESIGNER = "UI Designer"
        val EVENT_CATEGORIES = arrayOf(BUILDER_OF_AZKABAN, AUTOMOTIVE_PHILOSOPHER, CHAMBER_OF_CODERS, HALF_WAVE_PRINCE, MAD_HOLLOWS, SCAMANDERS_SUITCASE, ORDER_OF_OHMS, GOBLET_OF_WORKSHOPS)
        val DEVELOPERS = arrayOf(DEVELOPER_HEAD, ANDROID_DEVELOPER, UI_DESIGNER, WEB_DEVELOPER)
        fun getAnimation(context: Context?): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.button_animation)
        }
    }
}
