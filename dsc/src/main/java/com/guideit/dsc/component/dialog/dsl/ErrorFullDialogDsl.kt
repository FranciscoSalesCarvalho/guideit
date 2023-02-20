package com.guideit.dsc.component.dialog.dsl

import androidx.fragment.app.FragmentActivity
import com.guideit.dsc.component.dialog.ErrorFullDialog
import com.guideit.dsc.component.dialog.args.ErrorFullDialogArgs

fun FragmentActivity.showErrorFullDialog(
    apply: ErrorFullDialogArgs.() -> Unit
) = ErrorFullDialog().apply {
    apply(args)
    show(supportFragmentManager, null)
}