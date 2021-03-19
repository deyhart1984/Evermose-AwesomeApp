package com.evermos.awesome.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.EditText
import com.afollestad.materialdialogs.MaterialDialog
import com.evermos.awesome.R
import com.google.android.material.textfield.TextInputEditText

object CommonUtils {
    private val TAG = "CommonUtils"

    fun disableEditText(editText: TextInputEditText) {
        editText.isFocusable = false
        editText.isCursorVisible = false
    }

    fun disableEditText(editText: EditText) {
        editText.isFocusable = false
        editText.isCursorVisible = false
    }

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun showDownloadDialog(context: Context, value: String): MaterialDialog {

        return MaterialDialog.Builder(context)
            .content(value)
            .progress(false, 100, false)
            .progressIndeterminateStyle(true)
            .build()
    }

    fun showLocationDialog(context: Context?): MaterialDialog? {
        return MaterialDialog.Builder(context!!)
            .content(R.string.load_location)
            .progress(true, 0).build()
    }
}
