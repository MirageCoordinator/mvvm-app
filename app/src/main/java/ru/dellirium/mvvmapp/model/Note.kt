package ru.dellirium.mvvmapp.model

import android.os.Parcelable
import androidx.annotation.IntDef
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ru.dellirium.mvvmapp.BR

@Parcelize
data class Note(val id: String,
                var _title: String,
                var _text: String,
                @NoteColor val color: Int = WHITE) : BaseObservable(), Parcelable {

    companion object {
        const val WHITE = android.R.color.white
        const val YELLOW = android.R.color.holo_green_light
        const val BLUE = android.R.color.holo_blue_light
        const val RED = android.R.color.holo_red_light
        const val VIOLET = android.R.color.holo_purple
    }

    @IgnoredOnParcel
    @get:Bindable
    var title
    get() = _title
    set(value) {
        _title = value
        notifyPropertyChanged(BR.title)
    }

    @IgnoredOnParcel
    @get:Bindable
    var text
        get() = _text
        set(value) {
            _text = value
            notifyPropertyChanged(BR.text)
        }

    @IntDef(WHITE, YELLOW, BLUE, RED, VIOLET)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class NoteColor

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        return true
    }
}