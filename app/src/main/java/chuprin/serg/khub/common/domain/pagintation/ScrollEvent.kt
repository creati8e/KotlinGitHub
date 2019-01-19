package chuprin.serg.khub.common.domain.pagintation

data class ScrollEvent(
    val lastVisible: Int,
    val itemsCount: Int
)