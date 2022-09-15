package com.example.yana.culculatorhome.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yana.culculatorhome.data.MathOperatorsType

class MainViewModel : ViewModel() {

    val data = arrayListOf<String>()
    private var firstValue: Long = 0

    val totalResult = MutableLiveData<String>()
    val result = MutableLiveData<String>()

    fun pokaz(stroka: String, ochistka: Boolean) {
        if (stroka == MathOperatorsType.EQUALS.mathType) {

        } else {
            writeText(stroka)
        }
    }

    private fun writeText(stroka: String) {
        when (stroka) {
            MathOperatorsType.PLUS.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MathOperatorsType.PLUS.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            MathOperatorsType.MINUS.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MathOperatorsType.MINUS.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            MathOperatorsType.DIVIDE.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MathOperatorsType.DIVIDE.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            MathOperatorsType.MULTIPLY.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MathOperatorsType.MULTIPLY.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }

            else -> {
                if (data.isEmpty()) {
                    data.add(stroka)
                } else {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        item += stroka
                        data[data.size - 1] = item
                    } else {
                        data.add(stroka)
                    }
                }
            }
        }
        result.postValue(data.joinToString(separator = "") { it })
        calculate()
    }

    private fun calculate() {
        var result: Double = 0.0
        var lastOperator: String? = null
        data.forEach {
            if (isSymbol(it)) {
                lastOperator = it
            } else {
                if (lastOperator == null)
                    result = it.toDouble()
                else {
                    when (lastOperator) {
                        MathOperatorsType.MULTIPLY.mathType -> result *= it.toDouble()
                        MathOperatorsType.DIVIDE.mathType -> result /= it.toDouble()
                        MathOperatorsType.MINUS.mathType -> result -= it.toLong()
                        MathOperatorsType.PLUS.mathType -> result += it.toLong()
                    }
                }
            }
        }
        totalResult.postValue(result.toString())
    }

    private fun isNotSymbol(item: String): Boolean {
        return item != MathOperatorsType.MULTIPLY.mathType && item != MathOperatorsType.DIVIDE.mathType
                && item != MathOperatorsType.MINUS.mathType && item != MathOperatorsType.PLUS.mathType
    }

    private fun isSymbol(item: String): Boolean {
        return item == MathOperatorsType.MULTIPLY.mathType || item == MathOperatorsType.DIVIDE.mathType
                || item == MathOperatorsType.MINUS.mathType || item == MathOperatorsType.PLUS.mathType
    }
}