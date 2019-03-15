package net.techpda.gudle

import android.databinding.ObservableArrayList
import android.databinding.ObservableField

class CourseListItemViewModel(title: String) {

    var title: ObservableField<String> = ObservableField()

    init {
        this.title.set(title)
    }
}