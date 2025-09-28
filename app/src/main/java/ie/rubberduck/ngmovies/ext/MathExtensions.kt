package ie.rubberduck.ngmovies.ext

import kotlin.math.ceil
import kotlin.math.pow

fun Double.roundTo1Decimal(): Double {
    val factor = 10.0.pow(1)
    return ceil(this * factor) / factor
}