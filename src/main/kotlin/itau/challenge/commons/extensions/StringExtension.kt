package itau.challenge.commons.extensions

fun String.camelToSnakeCase() = this.replace("([a-z])([A-Z]+)".toRegex(), "$1_$2")
