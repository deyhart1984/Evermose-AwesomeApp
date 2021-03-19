package com.evermos.awesome.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.evermos.awesome.R
import java.io.ByteArrayOutputStream
import java.io.IOException

object ImageUtils {

    fun RequestOption(): RequestOptions {
        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.no_picture)
            .error(R.drawable.no_picture)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .priority(Priority.HIGH)
        return options
    }

    fun setImageBase64(contect: Context, imageView: ImageView): String {
        var encodedImage = ""
        try {
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            //bitmap = Compressor (this).setQuality(75).compressToBitmap(imageFile)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream . toByteArray ()
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)

            Log.println(1, "BASE_64", encodedImage)

        } catch (er : IOException) {
            er.printStackTrace()
            Log.e("BASE_64", "ERROR")
        }

        return encodedImage
    }
}