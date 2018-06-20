package com.force.rain.com.helloworld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix

object BitMapUtil {

    fun overdraw(background: Bitmap, foreground: Bitmap): Bitmap {
        val tmpCanvas = Bitmap.createBitmap(background.width, background.height, background.config)
        val canvas = Canvas(tmpCanvas)
        canvas.drawBitmap(background, Matrix(), null)
        canvas.drawBitmap(foreground, 0.0f, 0.0f, null)
        return tmpCanvas
    }
}