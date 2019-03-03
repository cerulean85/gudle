package techpda.net.cliplearning.extensions

import android.databinding.Observable
import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.os.Build
import android.support.annotation.RequiresApi
import java.util.*


class TextWatcherAdapter(private val field: ObservableField<String>) : TextWatcher {

    val value = ObservableField<String>()

    private var isInEditMode = false

    init {

        field.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                if (isInEditMode) {
                    return
                }
                value.set(field.get())
            }
        })
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        //
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        //
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun afterTextChanged(s: Editable) {
        if (!Objects.equals(field.get(), s.toString())) {
            isInEditMode = true
            field.set(s.toString())
            isInEditMode = false
        }
    }
}