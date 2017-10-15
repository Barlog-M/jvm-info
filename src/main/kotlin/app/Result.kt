package app

fun<T> ok(value: T) = Pair(false, value)

@Suppress("UNCHECKED_CAST")
fun<T> bad() = Pair(true, Unit as T)

fun<T> bad(value: T) = Pair(true, value)
