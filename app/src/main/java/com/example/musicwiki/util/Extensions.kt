package com.example.musicwiki.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

fun Context.toastMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.isInternetOn(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = cm.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}