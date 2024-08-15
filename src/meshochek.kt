import java.util.Objects
import kotlin.collections.List

const val PI = 3.14

interface Computing{
    fun computeArea() : Double
    fun computePerimeter() : Double
    override fun toString(): String
}

object FigureList{
    val figureList = ArrayList<Computing>()
}

open class Circle(private val r : Double) : Computing{
    override fun computeArea() : Double{return PI * r * r}
    override fun computePerimeter() : Double{return 2 * PI * r}
    override fun toString(): String{
        return "${this::class.simpleName} with radius $r"
    }
}

open class Square(private val r : Double) : Computing{
    override fun computeArea() : Double{return r * r}
    override fun computePerimeter() : Double{return 4 * r}
    override fun toString(): String{
        return "${this::class.simpleName} with property $r"
    }
}

class UnknownFigureTypeException : Exception()

class IllegalFigurePropertyException : Exception()

fun main(){
    while (true){
    val x = try {
        val a = readln().toInt()
        if (a < 0){ throw IllegalFigurePropertyException()}
        else a
    }
    catch (e : IllegalFigurePropertyException){
        println("IllegalFigurePropertyException")
    }

    when (x){
        1 -> {
            for (i in 0..<FigureList.figureList.size) {
                val figure = FigureList.figureList[i]
                println("$i ${FigureList.figureList[i]::class.simpleName} ${figure.computeArea()}")
            }
        }
        2 -> {
            for (i in 0..<FigureList.figureList.size){
                val figure = FigureList.figureList[i]
                println("$i ${FigureList.figureList[i]::class.simpleName} ${figure.computePerimeter()}")
            }
        }
        3 -> {
            val b = readln().toDouble()
            val figure = readln()
            try {
                when (figure) {
                    "circle" -> {
                        val circle = Circle(b)
                        FigureList.figureList.add(circle)
                    }
                    "square" -> {
                        val square = Square(b)
                        FigureList.figureList.add(square)
                    }
                    else -> throw UnknownFigureTypeException()
                }
            }
            catch (e: UnknownFigureTypeException){
                println("UnknownFigureTypeException")
            }
        }
        4 -> break
        }
    }
    println(FigureList.figureList[0])
}




