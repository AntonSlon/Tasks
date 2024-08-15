import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.lang.model.type.NullType
import kotlin.concurrent.thread

class ThreadTask : Thread(){
    override fun run() {
        println("${currentThread()}")
        sleep(300)
    }
}

class RunnableTask : Runnable {
    override fun run() {
        println("Thread started")
        repeat(2){
            println("${currentThread()} : $it ")
            sleep(300)
        }
    }
}

fun main() {
    val executor = Executors.newFixedThreadPool(3)
    repeat(5){
        val task = RunnableTask()
        executor.execute(task)
    }
    executor.shutdown()
}


