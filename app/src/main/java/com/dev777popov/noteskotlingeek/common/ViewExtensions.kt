package com.dev777popov.noteskotlingeek.common

import android.view.View

fun View.dip(value: Int) = (value * resources.displayMetrics.density).toInt()
fun View.dip(value: Float) = (value * resources.displayMetrics.density).toInt()