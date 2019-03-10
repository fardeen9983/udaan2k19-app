package `in`.ac.bvmengineering.udaan2k19.AR

import `in`.ac.bvmengineering.udaan2k19.DataClass.ARPoint
import `in`.ac.bvmengineering.udaan2k19.Helper.LocationHelper
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.location.Location
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import java.util.*


class AROverlayView(internal var context: Context) : GLSurfaceView(context) {
    private var rotatedProjectionMatrix = FloatArray(16)
    private var currentLocation: Location? = null
    private val arPoints: List<ARPoint>


    init {

        //Demo points
        arPoints = object : ArrayList<ARPoint>() {
            init {
                add(ARPoint("Sun Wheel", 16.0404856, 108.2262447, 0.0))
                add(ARPoint("Linh Ung Pagoda", 16.1072989, 108.2343984, 0.0))
                add(ARPoint("hey", 22.558297, 72.924685, 0.0))
            }
        }
    }

    fun updateRotatedProjectionMatrix(rotatedProjectionMatrix: FloatArray) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix
        this.invalidate()
    }

    fun updateCurrentLocation(currentLocation: Location) {
        this.currentLocation = currentLocation
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (currentLocation == null) {
            return
        }

        val radius = 30
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        paint.textSize = 60f

        for (i in arPoints.indices) {
            val currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation!!)
            val pointInECEF = LocationHelper.WSG84toECEF(arPoints[i].location)
            val pointInENU = LocationHelper.ECEFtoENU(currentLocation!!, currentLocationInECEF, pointInECEF)

            val cameraCoordinateVector = FloatArray(4)
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0)

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                val x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * width
                val y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * height

                canvas.drawCircle(x, y, radius.toFloat(), paint)
                canvas.drawText(arPoints[i].name, x - 30 * arPoints[i].name.length / 2, y - 80, paint)
            }
        }
    }
}
