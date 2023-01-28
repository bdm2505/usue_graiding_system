package ru.lytvest.kafedra

class Utils {

    companion object {


        fun toFio(fio: String): FIO {
            val arr = fio.split(" +".toRegex())
            if (arr.size >= 3) {
                return FIO(arr[1], arr[0], arr.subList(2, arr.lastIndex).joinToString(" "))
            }
            if (arr.size == 2) {
                return FIO(arr[1], arr[0], "")
            }
            return FIO(arr[0], "", "")
        }
    }
}

data class FIO(val first: String, val last: String, val pat: String)