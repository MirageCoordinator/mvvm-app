package ru.dellirium.mvvmapp.common

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:note_background")
fun setBackground(view: View, background: Int?) {
    if (background != null && background != 0) {
        view.setBackgroundColor(ContextCompat.getColor(view.context, background))
    }
}