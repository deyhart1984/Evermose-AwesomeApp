package com.evermos.awesome.ui.appversion

import com.evermos.awesome.data.network.model.appversion.Version

interface AppVersionView {
    fun checkSuccess(data: Version.Data)
    fun responFailed(msg: String)
    fun showDownloadDialog()
    fun updateDownloadDialog(progress: Int)
    fun hideDownloadDialog()
    fun downloadSuccess(path: String)
}
