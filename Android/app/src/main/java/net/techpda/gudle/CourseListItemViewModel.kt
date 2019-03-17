package net.techpda.gudle

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField

class CourseListItemViewModel(private val clip: Clip): BaseObservable() {

    var title: String?
        @Bindable
        get() = clip.title
        set(title) {
            clip.title = title!!
            notifyPropertyChanged(BR.title)
        }
}
