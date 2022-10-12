@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
/*    when {
        n < 10 -> 1
        else -> digitNumber(n / 10) + digitNumber(n % 10)
*/
    var count = 0
    var number = n
    do {
        count++
        number /= 10
    } while (number != 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) {
        return 1
    }
    var result1 = 1
    var result2 = 1
    var resultSum: Int
    for (i in 3..n) {
        resultSum = result1 + result2
        result1 = result2
        result2 = resultSum
    }
    return result2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val maxDigit = max(m,n)
    val minDigit = min(m,n)
    if (maxDigit % minDigit == 0) {
        return maxDigit
    }
    var residual = maxDigit % minDigit
    var koef = minDigit
    var accessory = residual
    do {
        residual = koef % residual
        koef = accessory
        accessory = residual
    } while (residual != 0)
    return (m * n) / koef
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) {
        return 2
    }
    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0) {
            return i
        }
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    if (n % 2 == 0) {
        return n / 2
    }
    for (i in sqrt(n.toDouble()).toInt() downTo 3 step 2) {
        if (n % i == 0) {
            return n / i
        }
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if ((m < 2) || (n < 2)) {
        return false
    }
    val maxDigit = max(m,n)
    val minDigit = min(m,n)
    var residual = maxDigit % minDigit
    var koef = minDigit
    var accessory = residual
    if (maxDigit % minDigit == 0) {
        return false
    }
        while (residual != 1) {
            residual = koef % residual
            if (residual == 0) {
                return false
            }
            koef = accessory
            accessory = residual
        }
        return residual == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val rootM = sqrt(m.toDouble()).toInt()
    val rootN = sqrt(n.toDouble()).toInt()
    return (((rootM * rootM) in m..n) || ((rootN * rootN) in m..n))
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var nextVal = x
    var step = 0
    while (nextVal != 1) {
        if (nextVal % 2 == 0) {
            nextVal /= 2
        }
        else {
            nextVal = 3 * nextVal + 1
        }
        step++
    }
    return step
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */

fun sin(x: Double, eps: Double): Double {
    val y = (180.0 * x) / PI
    if (y % 180.0 == 0.0) return 0.0
    var sign = 1.0
    var nextVal = x
    var error: Double
    var degree = 1
    var xDegree: Double
    do {
        degree += 2
        sign *= -1.0
        xDegree = 1.0
        for (i in 1 .. degree) {
            xDegree *= x
        }
        nextVal += sign * xDegree / factorial(degree)
        error = xDegree / factorial(degree)
    } while (eps < error)
    return nextVal
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */

fun cos(x: Double, eps: Double): Double {
    val y = (180.0 * x) / PI
    if (y % 360.0 == 0.0) return 1.0
    if (y % 360.0 == 180.0) return -1.0
    var sign = 1.0
    var nextVal = 1.0
    var error: Double
    var degree = 0
    var xDegree: Double
    do {
        degree += 2
        sign *= -1.0
        xDegree = 1.0
        for (i in 1 .. degree) {
            xDegree *= x
        }
        nextVal += sign * xDegree / factorial(degree)
        error = xDegree / factorial(degree)
    } while (eps < error)
    return nextVal
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun revert(n: Int): Int {
    if (n < 10) {
        return n
    }
    var value: Int
    var residual = n
    var result = 0
    var rank = 1
    for (i in 1 until digitNumber(n)) {
        rank *= 10
    }
    do {
        value = residual % 10
        result += value * rank
        rank /= 10
        residual /= 10
    } while (rank != 0)
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean /*= n == revert(n)*/{
    if (n < 10) {
        return true
    }
    var value: Int
    var residual = n
    var result = 0
    var rank = 1
    for (i in 1 until digitNumber(n)) {
        rank *= 10
    }
    do {
        value = residual % 10
        result += value * rank
        rank /= 10
        residual /= 10
    } while (rank != 0)
    return n == result
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digit = n % 10
    return digitCountInNumber(n, digit) != digitNumber(n)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var square: Int
    var count: Int
    var digit = 1
    var digNum = 0
    var answer = 1
    while (digNum < n) {
        square = digit * digit
        digit++
        digNum += digitNumber(square)
        if (digNum > n) {
            count = digNum - n
            do {
                count--
                square /= 10
                answer = square % 10
            } while (count != 0)
            return answer
        }
        else {
            answer = square % 10
        }
    }
    return answer
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var fibVal: Int
    var count: Int
    var digit = 1
    var digNum = 0
    var answer = 1
    while (digNum < n) {
        fibVal = fib(digit)
        digit++
        digNum += digitNumber(fibVal)
        if (digNum > n) {
            count = digNum - n
            do {
                count--
                fibVal /= 10
                answer = fibVal % 10
            } while (count != 0)
            return answer
        }
        else {
            answer = fibVal % 10
        }
    }
    return answer
}