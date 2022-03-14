package com.example.calculater.extra

import java.util.*
import java.util.regex.Pattern
import kotlin.math.pow

class MADSCalculator {
    private var operators: ArrayList<Char> = ArrayList()
    fun findPostfix(input: String): String {
        var inputP = input
        if (!validateInput(inputP)) return "Invalid expression"
        var temp = ""
        val al = ArrayList<Char>()
        al.add('(')
        var ch: Char
        inputP = "$inputP)"
        var i = 0
        while (i < inputP.length) {
            ch = inputP[i]
            when {
                ch == '(' -> al.add('(')
                operators.contains(ch) -> {
                    al.add(ch)
                    temp += pop(al, ch)
                }
                ch == ')' -> temp += pop(al)
                else -> {
                    while (ch != '(' && ch != ')' && !operators.contains(ch)) {
                        temp += ch
                        i += 1
                        ch = inputP[i]
                    }
                    temp = "$temp "
                    i -= 1
                }
            }
            i++
        }
        return temp
    }

    private fun pop(
        al: ArrayList<Char>,
        ch: Char
    ): String {
        var temp = ""
        var i = al.size - 1
        while (al[i] != '(' && i >= 0) {
            if (operators.indexOf(al[i]) < operators.indexOf(ch)) {
                temp += al[i]
                al.removeAt(i)
            }
            i--
        }
        return temp
    }

    private fun validateInput(input: String): Boolean {
        if (input.replace("[^(]".toRegex(), "").length != input.replace(
                "[^)]".toRegex(),
                ""
            ).length || input.split("[^\\d]").toTypedArray().isEmpty() || input.isEmpty()
        ) return false

        var pattern = Pattern.compile("(\\d\\()|(\\)\\d)|(\\([*/+^-])|([*/+^-]\\))")
        var matcher = pattern.matcher(input)
        if (matcher.find()) return false

        //
        pattern = Pattern.compile("[^\\d|+*^()/-]")
        matcher = pattern.matcher(input)
        if (matcher.find()) return false


        //to check if 2 consecutive operators are there...
        pattern = Pattern.compile("[*-+^/]{2}")
        matcher = pattern.matcher(input)
        return !matcher.find()
    }

    private fun pop(al: ArrayList<Char>): String // pop all operators until '(' reached and add them to temp
    {
        var temp = ""
        var i = al.size - 1
        while (al[i] != '(' && i >= 0) {

            temp += al[i]
            al.removeAt(i)
            i--
        }
        al.removeAt(al.size - 1)
        return temp
    }

    fun evaluatePostfix(inputP: String): Float //this will return the final result after evaluating postfix expression
    {
        val stack = Stack<Float>()
        var ch: Char
        var inputA: Float
        var inputB: Float
        var i = 0
        while (i < inputP.length) {
            ch = inputP[i]
            if (operators.contains(ch)) {
                inputB = stack.pop()
                inputA = stack.pop()
                when (ch) {
                    '^' -> stack.push(
                        inputA.toDouble().pow(inputB.toDouble()).toFloat()
                    )
                    '*' -> stack.push(inputA * inputB)
                    '+' -> stack.push(inputA + inputB)
                    '/' -> stack.push(inputA / inputB)
                    '-' -> stack.push(inputA - inputB)
                }
            } else {
                var temp = ""
                while (!operators.contains(ch) && i < inputP.length && ch != ' ') {
                    temp += ch
                    i += 1
                    ch = inputP[i]
                }
                stack.push(temp.toFloat())
            }
            i++
        }
        return stack.pop()
    }


    init {
        operators.add('^')
        operators.add('*')
        operators.add('+')
        operators.add('/')
        operators.add('-')
    }
}