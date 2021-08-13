package alex.pakshin.hhtesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt
import android.text.method.ScrollingMovementMethod




class MainActivity : AppCompatActivity() {
    private val arraySizes = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printMassive.movementMethod = ScrollingMovementMethod()
        button.setOnClickListener {
            printMassive.text = printArray(massiv(massiveSize.text.toString().toInt()))
        }
    }

    private fun isEven(n: Int) = n % 2 == 0

    private fun generateSize(): Int {
        val currentSize = Random.nextInt(1..40)
        if (arraySizes.find { it == currentSize } == null) {
            return currentSize
        } else {
            generateSize()
        }
        return 0
    }

    private fun bubbleSort(array: Array<Int>, byAsc: Boolean = true): Array<Int> {
        if (array.isEmpty()) return array
        var isSorted = false
        var counter = 0

        while (!isSorted) {
            isSorted = true
            for (i in 0 until array.size - 1 - counter) {
                if (byAsc) {
                    if (array[i] > array[i + 1]) {
                        isSorted = false
                        swap(array, i, i + 1)
                    }
                } else {
                    if (array[i] < array[i + 1]) {
                        isSorted = false
                        swap(array, i, i + 1)
                    }
                }
            }
            counter++
        }
        return array
    }

    private fun swap(array: Array<Int>, i: Int, j: Int) {
        val temp = array[j]
        array[j] = array[i]
        array[i] = temp
    }


    private fun massiv(n: Int): ArrayList<Array<Int>> {
        val resultArray = arrayListOf<Array<Int>>()
        val array = Array(n) {
            Array(generateSize()) {
                Random.nextInt(1..100)
            }
        }

        array.forEachIndexed { index, item ->
            if (isEven(index)) {
                resultArray.add(bubbleSort(item))
            } else {
                resultArray.add(bubbleSort(item, false))
            }
        }

        return resultArray
    }

    private fun printArray(array: ArrayList<Array<Int>>): String {
        var result = "\n"
        array.forEach { nestedArray ->
            nestedArray.forEach {
                result += "$it "
            }
            result += "\n\n"
        }
        return result
    }

}