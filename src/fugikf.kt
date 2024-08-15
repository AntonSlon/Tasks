import java.io.File


data class User(private val name : String, val age : Int)

class Person(val own : User)

object Json{
    private val file = File("test.json")
    private var jsonObj = ""
    fun <T>writeToFile(obj : T) where T : Any{
        val ownerList = obj::class.java.declaredFields.map { it }
        ownerList.forEach { println(it) }
        if (ownerList.isNotEmpty()) {
            val getList = ownerList.map { it.isAccessible = true ; it.get(obj) }
            getList.forEach {
                when (it){
                    is String -> jsonObj += "$it : ${it::class.java.simpleName} \n"
                    is Number -> jsonObj += "$it : ${it::class.java.simpleName} \n"
                    else -> { writeToFile(it) }
                }
            }
            file.writeText(jsonObj)
        }
    }
}

fun main(){
    val user1 = User("Viktor", 19)
    val person = Person(user1)
    Json.writeToFile(person)
}


