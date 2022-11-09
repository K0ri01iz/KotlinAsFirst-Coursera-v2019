@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.quadraticRootProduct
import lesson3.task1.digitNumber
import lesson3.task1.isPrime
import kotlin.math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    if (v.isEmpty()) {
        return 0.0
    }
    /*var result = 0.0
    for (i in 0 until v.size) {
        result += (v[i] * v[i])
    }
    return sqrt(result)*/
    val list = v.map { it * it }
    return sqrt(list.sum())
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    /*var result = 0.0
    return if (list.isNotEmpty()) {
        for (i in 0 until list.size) {
            result += list[i]
        }
        result / list.size
    } else 0.0*/
    /*return if (list.isNotEmpty()) {
        return list.sum() / list.size
    } else {
        0.0
    }*/
    when {
        list.isEmpty() -> 0.0
        else -> list.sum() / list.size
    }


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val meanList = mean(list)
    for (i in 0 until list.size) {
        list[i] -= meanList
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) {
        return 0
    }
    var result = p[0]
    var degree = 1
    for (i in 1 until p.size) {
        degree *= x
        result += p[i] * degree
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */


fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var element = n
    if (isPrime(n)) {
        list.add(n)
        return list
    }
    while (element != 1) {
        var i = 2
        while (element % i != 0) {
            i++
        }
        list.add(i)
        element /= i
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var digit: Int
    var residual = n % base
    var nextVal = n / base
    list.add(residual)
    while (nextVal != 0) {
        digit = nextVal
        residual = digit % base
        nextVal = digit / base
        list.add(0, residual)
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */

fun convertToString(n: Int, base: Int): String {
    if (n < 0) {
        return "ошибка"
    }
    val result = convert(n, base).map {
        if (it > 9) {
            ('a'.toInt() + it - 10).toChar()
        } else {
            (('0'.toInt() + it).toChar())
        }
    }
    return result.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    if (digits.size == 1) return digits.first()
    var result = digits.last()
    var grade = 1
    for (i in digits.size - 2 downTo 0) {
        grade *= base
        result += digits[i] * grade
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */

fun decimalFromString(str: String, base: Int): Int {
    val list = str.toList().map { it.toInt() }.toMutableList()
    for (i in list.indices) {
        if (list[i] >= 97) {
            list[i] = list[i] - 'a'.toInt() + 10
        } else {
            list[i] = list[i] - '0'.toInt()
        }
    }
    var result = list.last()
    var grade = 1
    for (i in list.size - 2 downTo 0) {
        grade *= base
        result += list[i] * grade
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun digitList(n: Int): MutableList<Int> {
    val digits = mutableListOf<Int>()
    var residual: Int
    var digit = n
    while (digit != 0) {
        residual = digit % 10
        digits.add(0, residual)
        digit /= 10
    }
    return digits
}

/*fun roman(n: Int): String {
    if ((n < 1) || (n > 3999)) return "ошибка"
    val romeSymbol = "IVXLCDM"
    val list = digitList(n)
    var romeDig = String()
    var step = 0
    var rank = when (list.size) {
        1 -> 0
        2 -> 2
        3 -> 4
        else -> 6
    }
    do {
        val element = list[step]
        if (element in 1..3) {
            for (i in 1..element) {
                romeDig += romeSymbol[0 + rank]
            }
        }
        if (element == 4) {
            for (i in 0..1) {
                romeDig += romeSymbol[i + rank]
            }
        }
        if (element == 5) romeDig += romeSymbol[1 + rank]
        if (element in 6..8) {
            romeDig += romeSymbol[1 + rank]
            for (i in 6..element) {
                romeDig += romeSymbol[0 + rank]
            }
        }
        if (element == 9) {
            for (i in 0..2 step 2) {
                romeDig += romeSymbol[i + rank]
            }
        }
        if (element == 0) romeDig += ""
        step++
        rank -= 2
    } while (step != list.size)
    return romeDig
}*/

fun romeDig (n: Int, deg: Int): MutableList<Int> {
    val list = mutableListOf<Int>()
    val digit = (10.0.pow(deg)).toInt()
    when (n) {
        in 1..3 -> {
            for (i in 1..n) {
                list.add(digit)
            }
        }
        4 -> {
            list.add(digit)
            list.add(5 * digit)
        }
        5 -> list.add(5 * digit)
        in 6..8 -> {
            list.add(5 * digit)
            for (i in 6..n) {
                list.add(digit)
            }
        }
        9 -> {
            list.add(digit)
            list.add(10 * digit)
        }
        else -> list.add(0)
    }
    return list
}
fun roman(n: Int): String {
    if ((n < 1) || (n > 3999)) return "ошибка"
    val list = mutableListOf<Int>()
    var deg = digitNumber(n) - 1
    var nextVal = n
    do {
        val digit = nextVal / (10.0.pow(deg)).toInt()
        nextVal %= (10.0.pow(deg)).toInt()
        list += romeDig(digit, deg)
        deg--
    } while (nextVal != 0)
    return list.map {
        when (it) {
            1000 -> "M"
            500 -> "D"
            100 -> "C"
            50 -> "L"
            10 -> "X"
            5 -> "V"
            1 -> "I"
            else -> ""
        }
    }.joinToString(separator = "")
}


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun russian(n: Int): String {
    if ((n < 1) || (n > 999999)) return "ошибка"
    val root = listOf("од", "дв", "три", "четыр", "пят", "шест", "сем", "восем", "девя")
    val oneEnd = listOf("ин", "а", "", "е", "ь", "ь", "ь", "ь", "ть")
    val tenEnd =
        listOf("иннадцать", "енадцать", "надцать", "надцать", "надцать", "надцать", "надцать", "надцать", "тнадцать")
    val dozEnd = listOf("десять", "адцать", "дцать", "сорок", "ьдесят", "ьдесят", "ьдесят", "ьдесят", "носто")
    val hunEnd = listOf("сто", "ести", "ста", "еста", "ьсот", "ьсот", "ьсот", "ьсот", "тьсот")
    val tousEnd = listOf("на", "е", "", "е", "ь", "ь", "ь", "ь", "ть")
    val tousandEnd = listOf("а", "и", "и", "и", "", "", "", "", "")
    val tousand = "тысяч"
    val list = digitList(n)
    var rusDig = ""
    var step = 0
    var rank = list.size
    do {
        val element = list[step]
        if ((rank == 6) || (rank == 3)) {
            when (element) {
                1 -> {
                    if (rank == 3) {
                        if (list.size > 3) rusDig += " "
                    }
                    rusDig += hunEnd[0]
                }

                0 -> rusDig += ""
                else -> {
                    if (rank == 3){
                        if (list.size > 3) rusDig += " "
                    }
                    rusDig += root[element - 1]
                    rusDig += hunEnd[element - 1]
                }
            }
        }
        if ((rank == 5) || (rank == 2)) {
            when (element) {
                1 -> {
                    if (list[step + 1] == 0) {
                        if (list.size > rank) rusDig += " "
                        rusDig += dozEnd[0]
                    } else {
                        if (list.size > rank) rusDig += " "
                        rusDig += root[list[step + 1] - 1]
                        rusDig += tenEnd[list[step + 1] - 1]
                        if (rank == 5) {
                            rusDig += " "
                            rusDig += tousand
                        }
                    }
                }

                0 -> rusDig += ""
                4 -> {
                    if (list.size > rank) rusDig += " "
                    rusDig += dozEnd[3]
                }

                else -> {
                    if (list.size > rank) rusDig += " "
                    rusDig += root[element - 1]
                    rusDig += dozEnd[element - 1]
                }
            }
        }
        if ((rank == 4) || (rank == 1)) {
            if (list.size == rank) {
                when (element) {
                    0 -> rusDig += ""
                    else -> {
                        rusDig += root[element - 1]
                        if (rank == 4) {
                            rusDig += tousEnd[element - 1]
                            rusDig += " "
                            rusDig += tousand
                            rusDig += tousandEnd[element - 1]
                        } else rusDig += oneEnd[element - 1]
                    }
                }
            } else {
                when (element) {
                    0 -> {
                        if (rank == 4) {
                            rusDig += " "
                            rusDig += tousand
                        } else rusDig += ""
                    }

                    else -> {
                        if (list[step - 1] == 1) rusDig += ""
                        else {
                            rusDig += " "
                            rusDig += root[element - 1]
                            if (rank == 4) {
                                rusDig += tousEnd[element - 1]
                                rusDig += " "
                                rusDig += tousand
                                rusDig += tousandEnd[element - 1]
                            } else rusDig += oneEnd[element - 1]
                        }
                    }
                }
            }
        }
        step++
        rank--
    } while (step != list.size)
    return rusDig
}

fun List<Int>.any(lambda: (n: Int) -> Boolean): Boolean {
    for (element in this) {
        if (lambda(element)) {
            return true
            }
    }
    return false
}