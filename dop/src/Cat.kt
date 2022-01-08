data class Cat(val name: String) {
    var color: String = "#FFFFFF"
    var age: Int = 0
    var weight: Int = 0

    constructor(name: String, color: String, age: Int, weight: Int) : this(name) {
        this.color = color
        this.age = age
        this.weight = weight
    }
}