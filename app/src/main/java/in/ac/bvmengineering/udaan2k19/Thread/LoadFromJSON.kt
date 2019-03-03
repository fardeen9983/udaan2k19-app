package `in`.ac.bvmengineering.udaan2k19.Thread

import `in`.ac.bvmengineering.udaan2k19.DataClass.Developer
import `in`.ac.bvmengineering.udaan2k19.DataClass.Event
import `in`.ac.bvmengineering.udaan2k19.DataClass.EventCategory
import `in`.ac.bvmengineering.udaan2k19.DataClass.Manager
import `in`.ac.bvmengineering.udaan2k19.Misc.Constant
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.preference.PowerPreference
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class LoadFromJSON : AsyncTask<Context, Void, Void>() {

    private lateinit var builder: EventCategory
    private lateinit var chamber: EventCategory
    private lateinit var halfwave: EventCategory
    private lateinit var scamander: EventCategory
    private lateinit var mad: EventCategory
    private lateinit var ohms: EventCategory
    private lateinit var automotive: EventCategory
    private lateinit var workshop: EventCategory
    private val TAG = javaClass.simpleName
    override fun doInBackground(vararg contexts: Context): Void? {
        val context = contexts[0]
        try {
            //Reading from events.json file
            var input = context.assets.open("events.json")
            var buffer = ByteArray(input.available())
            input.read(buffer)
            input.close()
            val json = JSONArray(String(buffer))
            Log.v(TAG, json.toString())
            val events = HashMap<String, Event>()
            //serializing event details into objects
            val eventCategories = ArrayList<EventCategory>()
            workshop = EventCategory(R.drawable.goblet_of_workshop, context.getString(R.string.cat_workshop))
            builder = EventCategory(R.drawable.builder_of_azkaban, context.getString(R.string.cat_civil))
            chamber = EventCategory(R.drawable.chamber_of_coders, context.getString(R.string.cat_cp_it))
            halfwave = EventCategory(R.drawable.half_wave_prince, context.getString(R.string.cat_ec_el))
            scamander = EventCategory(R.drawable.scamander_suitcase, context.getString(R.string.cat_non_tech))
            mad = EventCategory(R.drawable.mad_hollows, context.getString(R.string.cat_cultural))
            ohms = EventCategory(R.drawable.order_of_ohms, context.getString(R.string.cat_ee))
            automotive = EventCategory(R.drawable.automotive_philosopher, context.getString(R.string.cat_mech_prod))
            for (i in 0 until json.length()) {
                val event = Gson().fromJson<Event>(json.optJSONObject(i).toString(), Event::class.java)
                for (j in Constant.EVENT_CATEGORIES.indices) {
                    if (event.dept == Constant.EVENT_CATEGORIES[j]) {
                        save(event, j)
                        break
                    }
                }
                Log.v(TAG, event.toString())
            }

            input = context.assets.open("head.json")
            buffer = ByteArray(input.available())
            input.read(buffer)
            input.close()
            val head = JSONObject(String(buffer))
            for (string in Constant.EVENT_CATEGORIES) {
                if (string == Constant.SCAMANDERS_SUITCASE || string == Constant.GOBLET_OF_WORKSHOPS)
                    continue
                val array = head.optJSONArray(string)
                for (i in 0 until array.length()) {
                    val manager = Gson().fromJson<Manager>(array.optJSONObject(i).toString(), Manager::class.java)
                    saveHead(manager, string)
                }
            }
            eventCategories.add(builder)
            eventCategories.add(automotive)
            eventCategories.add(chamber)
            eventCategories.add(halfwave)
            eventCategories.add(mad)
            eventCategories.add(scamander)
            eventCategories.add(ohms)
            eventCategories.add(workshop)
            PowerPreference.getDefaultFile().put("eventCategories", eventCategories)
            val developers = ArrayList<Developer>()
            input = context.assets.open("developer.json")
            buffer = ByteArray(input.available())
            input.read(buffer)
            input.close()
            val temp = JSONObject(String(buffer))
            for (string in Constant.DEVELOPERS) {
                val array = temp.optJSONArray(string)
                for (i in 0 until array.length()) {
                    val developer = Gson().fromJson<Developer>(array.optJSONObject(i).toString(), Developer::class.java)
                    when (string) {
                        Constant.DEVELOPER_HEAD -> {
                            developer.type = Constant.DEVELOPER_HEAD
                            developer.imageID = R.drawable.programming_icon
                        }
                        Constant.ANDROID_DEVELOPER -> {
                            developer.type = Constant.ANDROID_DEVELOPER
                            developer.imageID = R.drawable.android_developer_logo
                        }
                        Constant.UI_DESIGNER -> {
                            developer.type = Constant.UI_DESIGNER
                            developer.imageID = R.drawable.graphicsdesigner_icon
                        }
                        Constant.WEB_DEVELOPER -> {
                            developer.type = Constant.WEB_DEVELOPER
                            developer.imageID = R.drawable.programming_icon
                        }
                    }
                    developers.add(developer)
                }
            }
            PowerPreference.getDefaultFile().put("developers", developers)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

    private fun save(event: Event, i: Int) {
        when (i) {
            0 -> builder.events.add(event)
            1 -> automotive.events.add(event)
            2 -> chamber.events.add(event)
            3 -> halfwave.events.add(event)
            4 -> mad.events.add(event)
            5 -> scamander.events.add(event)
            6 -> ohms.events.add(event)
            7 -> workshop.events.add(event)
        }
    }

    private fun saveHead(manager: Manager, i: String) {
        when (i) {
            Constant.BUILDER_OF_AZKABAN -> builder.managers.add(manager)
            Constant.AUTOMOTIVE_PHILOSOPHER -> automotive.managers.add(manager)
            Constant.CHAMBER_OF_CODERS -> chamber.managers.add(manager)
            Constant.HALF_WAVE_PRINCE -> halfwave.managers.add(manager)
            Constant.MAD_HOLLOWS -> mad.managers.add(manager)
            Constant.ORDER_OF_OHMS -> ohms.managers.add(manager)
        }
    }
}
