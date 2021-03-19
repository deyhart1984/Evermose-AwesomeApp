package com.evermos.awesome.ui.appversion

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.evermos.awesome.data.network.model.appversion.Version
import com.evermos.awesome.ui.main.MainActivity
import com.evermos.awesome.ui.splash.SplashActivity
import com.evermos.awesome.utils.CommonUtils
import com.evermos.awesome.utils.ToastMessageUtils

import java.io.File

class AppVersion : AppVersionView {
    lateinit var mProgressDownload: MaterialDialog
    lateinit var mProgressDialog: ProgressDialog
    lateinit var mPresenter: AppVersionPresenter
    internal var type = 0
    internal var fileName: String? = null

    lateinit var context: Context

    fun Check(context: Context) {
        this.context = context
        setUp()

        showLoading()
        mPresenter.getVersion()
    }

    protected fun setUp() {
        mPresenter = AppVersionPresenter(this)
        mProgressDownload = CommonUtils.showDownloadDialog(context, "Proses download..")
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(context)
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun checkSuccess(data: Version.Data) {
        hideLoading()
        showDialog(data.message, data.type, data.fileName)
    }

    override fun responFailed(msg: String) {
        hideLoading()
        if (msg != "") {
            ToastMessageUtils.message(context, msg)
        }

        val intent = MainActivity.getStartIntent(context)
        context.startActivity(intent)

        SplashActivity.fa.finish()
    }

    fun download() {
        if (type == 1) {
            val appPackageName = context.packageName // getPackageName() from Context or Activity object
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }

            SplashActivity.fa.finish()
        } else if (type == 2) {
            downloadFromServer()
        } else {
            ToastMessageUtils.message(context, "Tidak ada type download")
        }
    }

    fun showDialog(msg: String?, type: Int, fileName: String?) {
        this.type = type
        this.fileName = fileName

        val builder1 = AlertDialog.Builder(context)
        builder1.setMessage(msg)
        builder1.setCancelable(false)

        builder1.setPositiveButton(
            "Ya"
        ) { dialog, id ->
            download()
            dialog.cancel()
        }

        builder1.setNegativeButton(
            "Tidak"
        ) { dialog, id ->
            val intent = MainActivity.getStartIntent(context)
            context.startActivity(intent)

            SplashActivity.fa.finish()
            dialog.cancel()
        }

        val alert11 = builder1.create()
        alert11.show()
    }

    fun downloadFromServer() {
        mPresenter.downloadFromServer(context, fileName!!)
    }

    override fun showDownloadDialog() {
        if (!mProgressDownload.isShowing)
            mProgressDownload.show()
    }

    override fun updateDownloadDialog(progress: Int) {
        if (mProgressDownload != null && mProgressDownload.isShowing)
            mProgressDownload.setProgress(progress)
    }

    override fun hideDownloadDialog() {
        if (mProgressDownload != null && mProgressDownload.isShowing) {
            mProgressDownload.dismiss()
            mProgressDownload.setProgress(0)
        }
    }

    override fun downloadSuccess(path: String) {
        ToastMessageUtils.message(context, path)

        SplashActivity.fa.finish()

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(File("$path/$fileName")), "application/vnd.android.package-archive")
        intent.flags = intent.flags
        context.startActivity(intent)
    }
}
