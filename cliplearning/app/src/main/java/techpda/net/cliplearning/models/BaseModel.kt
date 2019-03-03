package techpda.net.cliplearning.models

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

open class BaseModel: BaseObservable()
{
    @field:SerializedName("is_success") val isSuccess: Boolean = false
    @field:SerializedName("is_open")val isOpen: Boolean = false
}