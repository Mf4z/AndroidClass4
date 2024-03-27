package fr.epita.adroidclass4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WSInterface {
    @GET("todos")
    fun getAllTodoItems() : Call<List<ToDoItem>>

    @GET("todos")
    fun getTodosForUser(@Query("userId") userId: Int) : Call<List<ToDoItem>>

}