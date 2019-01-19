package chuprin.serg.khub.main.repositories.view.argument

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Sergey Chuprin
 */
@Parcelize
class RepositoriesListArguments(
    val login: String
) : Parcelable