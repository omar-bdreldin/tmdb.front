package com.omarbadreldin.teldamoviestask.ui.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.omarbadreldin.base.mvi.common.ErrorState
import com.omarbadreldin.teldamoviestask.R

fun showDialog(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: Pair<String, () -> Unit>? = null,
    negativeButton: Pair<String, () -> Unit>? = null,
) {
    MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
        .apply {
            if (positiveButton != null) setPositiveButton(positiveButton.first) { dialog, _ ->
                positiveButton.second.invoke()
                dialog.dismiss()
            }

            if (negativeButton != null) setNegativeButton(negativeButton.first) { dialog, _ ->
                negativeButton.second.invoke()
                dialog.dismiss()
            }
        }.show()
}

fun ErrorState.showDialog(
    context: Context,
    positiveButton: Pair<String, () -> Unit>? = context.getString(R.string.label_ok) to { },
    negativeButton: Pair<String, () -> Unit>? = null,
) {
    showDialog(
        context,
        context.getString(R.string.label_error_occured),
        error.message,
        positiveButton,
        negativeButton
    )
}