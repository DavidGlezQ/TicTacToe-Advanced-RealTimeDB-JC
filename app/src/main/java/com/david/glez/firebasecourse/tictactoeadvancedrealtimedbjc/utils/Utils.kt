package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.utils

import android.icu.util.Calendar

fun createUserId () = Calendar.getInstance().timeInMillis.hashCode().toString()
