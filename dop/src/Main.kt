import java.util.*
import kotlin.math.abs

class Main {
    private val data: List<MutableList<Pair<String, Cat>>> = List(1000) { LinkedList() }

    private fun printCat(cat: Cat, name: String){
        println(name + ": {age: " + cat.age + ", color: " + cat.color + ", weight: " + cat.weight + "}")
    }

    private fun printCat(p: Pair<String, Cat>){
        printCat(p.second, p.first)
    }

    private fun hash(s: String): Int{
        val h = s.hashCode() % 1000
        if(h < 0){
            return h + 1000
        }
        return h
    }

    private fun binFirst(name: String): Pair<String, Cat>?{
        val idx = binFind(name)
        if(idx < 0){
            return null
        }
        return data[hash(name)][binFind(name)]
    }


    private fun binFind(name: String): Int{
        return data[hash(name)].binarySearch(
            Pair(name, Cat("none", -1, -1f)),
            compareBy{it.first}
        )
    }

    private fun resolveCollision(name: String, cat: Cat){
        val idx = -binFind(name) - 1 // Read binarySearch docs
        if(idx < 0){
            println("Create: already exists")
        } else{
            data[hash(name)].add(idx, Pair(name, cat))
            printCat(cat, name)
        }
    }

    fun create(name: String, color: String, age: Int, weight: Float){
        val cat = Cat(color, age, weight)

        if(data[hash(name)].isNotEmpty()){
            resolveCollision(name, cat)
        } else{
            data[hash(name)].add(Pair(name, cat))
            printCat(cat, name)
        }
    }

    fun read(name: String){
        val pair = binFirst(name)
        if(pair == null){
            println("Read: not found")
        } else{
            printCat(pair)
        }
    }

    fun delete(name: String){
        val idx = binFind(name)
        if(idx < 0){
            println("Delete: not found")
        } else{
            data[hash(name)].removeAt(idx)
            println("OK")
        }
    }

    fun readAll(){
        for(i in data){
            for(k in i){
                printCat(k)
            }
        }
    }

    fun update(name: String, color: String, age: Int, weight: Float){
        val idx = binFind(name)
        if(idx < 0){
            println("Update: not found")
        } else{
            data[hash(name)][idx] = Pair(name, Cat(color, age, weight))
        }
    }

    fun whereWeight(weight: Float){
        val epsilon = 0.001f
        for(list in data){
            for(cat in list){
                if(abs(cat.second.weight - weight) < epsilon){
                    printCat(cat)
                }
            }
        }
    }
}

fun getName(x: Int): String{
    var tmp = x
    var res = ""
    while(tmp >= 20){
        res += "z"
        tmp -= 20
    }
    res += ('a' + tmp)
    return res
}

fun main(){
    val proc = Main()
    while(true){
        val s = readLine()?.split(" ")
        if(s == null){
            break
        } else if(s[0] == "create"){
            proc.create(s[1], s[2], s[3].toInt(), s[4].toFloat())
        } else if(s[0] == "delete"){
            proc.delete(s[1])
        } else if(s[0] == "readall"){
            proc.readAll()
        } else if(s[0] == "read"){
            proc.read(s[1])
        } else if(s[0] == "update"){
            proc.update(s[1], s[2], s[3].toInt(), s[4].toFloat())
        } else if(s[0] == "where"){
            val tmp = s[1].split("=")
            proc.whereWeight(tmp[1].toFloat())
        }
    }
}
