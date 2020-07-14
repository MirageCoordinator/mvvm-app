package ru.dellirium.mvvmapp.model

import androidx.annotation.IntDef
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ru.dellirium.mvvmapp.BR

class Note(val id: String = "",
                title: String = "",
                text: String = "",
                @NoteColor val color: Int = WHITE) : BaseObservable() {

    companion object {
        const val WHITE = android.R.color.white
        const val YELLOW = android.R.color.holo_green_light
        const val BLUE = android.R.color.holo_blue_light
        const val RED = android.R.color.holo_red_light
        const val VIOLET = android.R.color.holo_purple
    }

    @Bindable
    var title: String = title
    set(value) {
        field = value
        notifyPropertyChanged(BR.title)
    }

    @Bindable
    var text: String = text
        set(value) {
            field = value
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

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + color
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}
