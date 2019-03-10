package `in`.ac.bvmengineering.udaan2k19.DataClass

import android.location.Location


class ARPoint(name: String, lat: Double, lon: Double, altitude: Double) {
    var location: Location
        internal set
    var name: String
        internal set

    init {
        this.name = name
        location = Location("ARPoint")
        location.latitude = lat
        location.longitude = lon
        location.altitude = altitude
    }
}
