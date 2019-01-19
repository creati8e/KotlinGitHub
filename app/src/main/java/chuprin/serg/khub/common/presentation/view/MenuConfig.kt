package crif.contact.core.api.presentation.view

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.MenuRes

inline fun menuConfig(block: MenuConfig.() -> Unit): MenuConfig = MenuConfig().apply(block)

class MenuConfig {

    @MenuRes
    val menuRes: Int? = null
    var onPrepareMenu: ((Menu) -> Unit)? = null
    var onMenuItemClick: ((MenuItem) -> Unit)? = null

}