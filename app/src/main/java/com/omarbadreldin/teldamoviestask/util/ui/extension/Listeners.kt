package com.omarbadreldin.teldamoviestask.util.ui.extension

import android.view.View

infix fun View.clicksTo(block: () -> Unit) {
    setOnClickListener {
        block()
    }
}