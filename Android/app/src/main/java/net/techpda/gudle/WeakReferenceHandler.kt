package net.techpda.gudle

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

abstract  class WeakReferenceHandler<in T>(reference:T?) : Handler() {

    private var mReference:WeakReference<T>? = WeakReference<T>(reference)

    override fun handleMessage(msg: Message) {
        val reference: T? = mReference?.get()

        handleMessage(reference, msg)
    }

    protected abstract fun handleMessage(mReference:T?, msg:Message)

}