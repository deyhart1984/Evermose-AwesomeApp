package com.evermos.awesome.ui.appversion

import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.DownloadListener
import com.androidnetworking.interfaces.DownloadProgressListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.evermos.awesome.BuildConfig
import com.evermos.awesome.data.network.ApiEndPoint
import com.evermos.awesome.data.network.model.appversion.Version
import com.evermos.awesome.utils.FileHelperUtils
import org.json.JSONObject

class AppVersionPresenter(private val mView: AppVersionView) {

    fun getVersion() {
        AndroidNetworking.post(ApiEndPoint.ENDPOINT_APP_VERSION)
            .addBodyParameter("version", BuildConfig.VERSION_CODE.toString())
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val gson = Gson()
                    val responses = gson.fromJson<Version.Response>(response.toString(), Version.Response::class.java!!)
                    if (!responses.rc.equals("00")) {
                        mView.responFailed(responses.message!!)
                    } else {
                        Log.e("Sukses", "version")
                        mView.checkSuccess(responses.data!!)
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                    error.printStackTrace()
                    mView.responFailed("Error checking Version")
                }
            })
    }

    fun downloadFromServer(context: Context, fileName: String) {
        mView.showDownloadDialog()

        val dirPath = FileHelperUtils.getDirApkPath(context)

        AndroidNetworking.download(ApiEndPoint.ENDPOINT_APP_DOWNLOAD + "/" + fileName, dirPath, fileName)
            .setTag("downloadApp")
            .setPriority(Priority.HIGH)
            .build()
            .setDownloadProgressListener(object : DownloadProgressListener {

                private var prevProgress: Int = 0

                override fun onProgress(bytesDownloaded: Long, totalBytes: Long) {
                    // do anything with onProgress
                    val progress = (bytesDownloaded * 100 / totalBytes).toInt()
                    if (progress != prevProgress) {
                        mView.updateDownloadDialog(progress)
                        prevProgress = progress

                        Log.e(
                            "progress", "total = " + totalBytes +
                                    " loaded = " + bytesDownloaded + " percentage = " + progress + "%"
                        )
                    }
                }
            })
            .startDownload(object : DownloadListener {
                override fun onDownloadComplete() {
                    // do anything after completion
                    mView.updateDownloadDialog(100)
                    mView.hideDownloadDialog()
                    mView.downloadSuccess(dirPath)
                }

                override fun onError(error: ANError) {
                    // handle error
                    mView.hideDownloadDialog()
                    mView.responFailed("Error download Version")
                }
            })
    }
}
