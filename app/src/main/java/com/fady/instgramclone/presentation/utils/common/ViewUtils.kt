package com.fady.instgramclone.presentation.utils.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fady.instgramclone.R
import com.fady.instgramclone.databinding.ProgressDialogLayoutBinding
import com.google.android.material.snackbar.Snackbar
import com.tapadoo.alerter.Alerter
import java.text.SimpleDateFormat
import java.util.*


fun showLoadingDialog(activity: Activity?, hint: String?): Dialog? {
    if (activity == null || activity.isFinishing) {
        return null
    }

    val dialogBinding = ProgressDialogLayoutBinding.inflate(LayoutInflater.from(activity))

    val progressDialog = Dialog(activity)
    progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog.setContentView(dialogBinding.root)

    if (!hint.isNullOrEmpty()) {
        dialogBinding.tvHint.show()
        dialogBinding.tvHint.text = hint
    } else {
        dialogBinding.tvHint.hide()
    }

    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    progressDialog.show()

    return progressDialog
}

fun hideLoadingDialog(mProgressDialog: Dialog?, activity: Activity?) {
    if (activity != null && !activity.isFinishing && mProgressDialog != null && mProgressDialog.isShowing) {
        mProgressDialog.dismiss()
    }
}


fun View.show() {
    if (visibility == View.VISIBLE) return

    visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.hide() {
    if (visibility == View.GONE) return

    visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retryAction: (() -> Unit)? = null,
    noDataAction: (() -> Unit)? = null,
    noInternetAction: (() -> Unit)? = null
) {
    when (failure.failureStatus) {
        FailureStatus.EMPTY -> {
            noDataAction?.invoke()
        }

        FailureStatus.NO_INTERNET -> {
            noInternetAction?.invoke()

            showNoInternetAlert(requireActivity())
        }

        FailureStatus.API_FAIL, FailureStatus.OTHER -> {
            noDataAction?.invoke()

            requireView().showSnackBar(
                failure.message ?: resources.getString(R.string.some_error),
                resources.getString(R.string.retry),
                retryAction
            )
        }
    }
}

fun showNoInternetAlert(activity: Activity) {
    Alerter.create(activity).setTitle(activity.resources.getString(R.string.connection_error))
        .setText(activity.resources.getString(R.string.no_internet))
        .setIcon(R.drawable.ic_no_internet).setBackgroundColorRes(R.color.red)
        .enableClickAnimation(true).enableSwipeToDismiss().show()
}

fun View.showSnackBar(
    message: String, retryActionName: String? = null, action: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction(retryActionName) {
            it()
        }
    }
    snackBar.show()
}

fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = getColorCompat(color)
}

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.makeTitleValueSpannable(title: String, value: String?): SpannableString {
    val spannableString = SpannableString("$title $value")
    spannableString.setSpan(
        ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
        title.length,
        spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}



