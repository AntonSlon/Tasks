import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.concurrent.atomic.AtomicInteger
import javax.lang.model.type.NullType
import kotlin.concurrent.thread
import kotlin.random.Random


class DAO(private val array : MutableList<Int>, private val semaphore: Semaphore) : Thread(){
    override fun run(){
        semaphore.acquire()
        sleep(500)
        println("${array[Cnt.count.getAndIncrement()]} ${currentThread()} ${System.nanoTime()}")
        semaphore.release()
    }
}

object Cnt{
    var count = AtomicInteger(0)
}

fun main(){
    val array = arrayListOf<Int>()
    repeat(8){ array.add(Random.nextInt(0, 1000)) }
    println(array)
    val semaphore = Semaphore(5)
    val threadPool = Executors.newFixedThreadPool(5)
    repeat(array.size){
        val thread = DAO(array, semaphore)
        threadPool.execute(thread)
    }
    threadPool.shutdown()
}