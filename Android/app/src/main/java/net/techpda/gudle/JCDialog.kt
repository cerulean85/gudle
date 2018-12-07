package net.techpda.gudle

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import org.jetbrains.anko.toast

class JCDialog(val activity: Activity) {

    private val mActivity: Activity = activity
    fun exit()
    {
        val builder = AlertDialog.Builder(mActivity)
        builder.setTitle("알림")
        builder.setMessage("앱을 종료하시겠습니까?")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes") { _, _ ->

            activity.moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }

        builder.setNegativeButton("No") { _, _ ->

        }

        val dialog = builder.create()
        dialog.show()
    }

}