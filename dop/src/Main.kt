import java.util.*

class Main {
    private val data: MutableList<Pair<String, Cat>> = LinkedList()

    fun printCat(cat: Cat, name: String){
        println(name + ": {age: " + cat.age + ", color: " + cat.color + ", weight: " + cat.weight + "}")
    }

    fun find(name: String): Cat?{
        for(i in data){
            if(i.first == name){
                return i.second
            }
        }
        return null
    }

    fun create(name: String, color: String, age: Int, weight: Int){
        if(find(name) == null){
            data.add(Pair(name, Cat(color, age, weight)))
            printCat(data.last().second, data.last().first)
        } else{
            println("Create: already exists")
        }
    }

    fun read(name: String){
        val tmp = find(name)
        if(tmp != null){
            printCat(tmp, name)
        } else{
            println("Read: not found")
        }
    }

    fun delete(name: String){
        val tmp = find(name)
        if(tmp != null){
            data.remove(Pair(name, tmp))
            println("OK")
        } else{
            println("Delete: not found")
        }
    }

    fun readAll(){
        for(i in data){
            printCat(i.second, i.first)
        }
    }
}

fun main(){
    val proc = Main()
    while(true){
        val s = readLine()?.split(" ")
        if(s == null){
            break
        } else if(s[0] == "create"){
            proc.create(s[1], s[2], s[3].toInt(), s[4].toInt())
        } else if(s[0] == "delete"){
            proc.delete(s[1])
        } else if(s[0] == "readall"){
            proc.readAll()
        } else if(s[0] == "read"){
            proc.read(s[1])
        }
    }
}