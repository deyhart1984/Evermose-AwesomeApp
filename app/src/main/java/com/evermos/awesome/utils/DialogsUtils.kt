package com.evermos.awesome.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.evermos.awesome.R


class DialogsUtils {
    private var mContext: Context? = null

    fun DialogsUtils(context: Context?) {
        mContext = context
    }

    fun openAlertDialog(
        message: String,
        positiveBtn: String,
        negativeBtn: String,
        listener: OnDialogButtonClickListener) {

        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_message_action)
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)

        val mTvMessage = dialog.findViewById(R.id.tv_message) as TextView
        val btnOk = dialog.findViewById(R.id.btn_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_cancel) as Button

        mTvMessage.text = message
        btnOk.text = positiveBtn
        btnCancel.text = negativeBtn

        btnOk.setOnClickListener {
            listener.onPositiveButtonClicked()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            listener.onNegativeButtonClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    fun openAlertDialogSingleAction(
        message: String,
        positiveBtn: String,
        listener: OnDialogButtonSingleActionClickListener) {

        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_message_action)
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)

        val mTvMessage = dialog.findViewById(R.id.tv_message) as TextView
        val btnOk = dialog.findViewById(R.id.btn_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_cancel) as Button

        mTvMessage.text = message
        btnOk.text = positiveBtn
        btnCancel.visibility = View.GONE

        btnOk.setOnClickListener {
            listener.onPositiveButtonSingleActionClicked()
            dialog.dismiss()
        }

        dialog.show()
    }
}