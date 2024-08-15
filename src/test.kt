import java.awt.List
import java.util.ArrayList
import javax.print.attribute.standard.MediaSize.Other


class Transformer() {

    fun <T, K, Other> map(collection: T, inputFun: (K) -> Other ): kotlin.collections.List<Other> where T: Collection<K>{
        val mutableCollection : MutableList<Other> = mutableListOf()
        for (obj in collection){mutableCollection.add(inputFun(obj))}
        return mutableCollection
    }

    fun <K> filter(collection: Collection<K>, inputFun: (K) -> Boolean) : List {
        val copiedCollection: MutableList<K> = mutableListOf()
        for (obj in collection){if (inputFun(obj)) copiedCollection.add(obj)}
        return copiedCollection as List
    }

}

fun main() {
    val list = setOf(1,2,4)
    println(list::class)
    val transformer = Transformer()
    val a = transformer.map(list) { it*10 }
    println(a)
    println(a::class)
}














