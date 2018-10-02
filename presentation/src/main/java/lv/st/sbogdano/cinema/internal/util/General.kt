package lv.st.sbogdano.cinema.internal.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import lv.st.sbogdano.cinema.R

fun <T> lazyThreadSafetyNone(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun imageSize(context: Context): Pair<Int, Int> {

    val typedValue = TypedValue()
    context.resources.getValue(R.dimen.rows_count, typedValue, true)
    val columns = context.resources.getInteger(R.integer.columns_count)
    val imageWidth = context.resources.displayMetrics.widthPixels / columns
    val rowsCount = typedValue.float
    val actionBarHeight =
            when(context.theme.resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
                true -> TypedValue.complexToDimensionPixelSize(typedValue.data, context.resources.displayMetrics)
                false -> 0
            }
    val imageHeight = ((context.resources.displayMetrics.heightPixels - actionBarHeight) / rowsCount).toInt()
    return Pair(imageWidth, imageHeight)
}

fun numberOfColumns(): Int {
    val displayMetrics = DisplayMetrics()
    // You can change this divider to adjust the size of the poster
    val widthDivider = 500
    val width = displayMetrics.widthPixels
    val nColumns = width / widthDivider
    return if (nColumns < 2) 2 else nColumns //to keep the grid aspect
}
