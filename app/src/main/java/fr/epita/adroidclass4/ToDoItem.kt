package fr.epita.adroidclass4
//Adds a kind of toString method to it for easy readability
data class ToDoItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)