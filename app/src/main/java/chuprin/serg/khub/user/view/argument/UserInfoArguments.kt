package chuprin.serg.khub.user.view.argument

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Sergey Chuprin
 */
@Parcelize
class UserInfoArguments(
    val login: String
) : Parcelable