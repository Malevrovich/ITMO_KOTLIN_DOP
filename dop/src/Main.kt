class Main {
    val keys: Array<String> = Array(1000) {"none"}
    val values: Array<Cat?> = Array(1000) {null}
    var size = 0

    fun printCat(cat: Cat){
        println(cat.name + ": {" + "name: " + cat.name + ", age: " + cat.age + ", color: " + cat.color + ", weight: " + cat.weight + "}")
    }

    fun create(name: String, color: String, age: Int, weight: Int){
        val cat = Cat(name, color, age, weight)

        keys[size] = name
        values[size] = cat

        size++

        printCat(cat)
    }

    fun read(name: String){
        for(i in 0..999){
            if(keys[i] == name){
                printCat(values[i]!!)
                break
            }
        }
    }

    fun delete(name: String){
        for(i in 0..999){
            if(keys[i] == name){
                for(k in i..998){
                    keys[k] = keys[k+1]
                    values[k] = values[k+1]

                    if(keys[k] == "none"){
                        break
                    }
                }
                break
            }
        }
        println("OK")
    }

    fun readAll(){
        for(i in 0..1000){
            if(keys[i] == "none"){
                break
            }
            printCat(values[i]!!)
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