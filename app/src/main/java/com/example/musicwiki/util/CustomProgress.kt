package com.example.musicwiki.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import com.example.musicwiki.R

class CustomProgress {
    private var dialog: Dialog? = null

    fun show(context: Context) {
        try {
            if (dialog != null && dialog!!.isShowing) {
                return
            }
            dialog = Dialog(context)
            dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.material_progress_bar)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.setCancelable(false)
            val progressWithArrow: ProgressBar = dialog!!.findViewById(R.id.progress_bar)
            dialog!!.show()
        } catch (e: Exception) {
        }
    }

    fun dismiss() {
        try {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
        } catch (e: IllegalArgumentException) {
        }
    }

    companion object {
        private var mInstance: CustomProgress? = null

        @get:Synchronized
        val instance: CustomProgress?
            get() {
                if (mInstance == null) {
                    mInstance = CustomProgress()
                }
                return mInstance
            }
    }
}