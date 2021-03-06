package com.scootin.view.fragment.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.scootin.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CitywideCategoryDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.dialog_theme)

            builder.setTitle(R.string.select_category)
                .setIcon(R.drawable.ic_category_popup)
                .setSingleChoiceItems(getListItem(), 0) { dialog, which ->
                    Timber.i("Checked = $which")
                }.setPositiveButton(R.string.done) { dialog, id ->
                Timber.i("positive = $id")
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getListItem() =
        requireContext().resources.getStringArray(R.array.citywide_delivery_options)

}