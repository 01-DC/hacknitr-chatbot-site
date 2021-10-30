package com.project.alexia

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

class LoadingDialog(mActivity: Activity) {

    private var activity: Activity = mActivity
    lateinit var dialog: AlertDialog

    fun startLoadingDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}