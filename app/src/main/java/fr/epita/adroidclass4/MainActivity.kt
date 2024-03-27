package fr.epita.adroidclass4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            }

        //        My code
        val calllwsButton : Button = findViewById(R.id.button_ws)
        calllwsButton.setOnClickListener{
            val serverUrl :String = "https://jsonplaceholder.typicode.com/"
            val retrofitClient = Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

            val service = retrofitClient.create(WSInterface::class.java)
            val callback: Callback<List<ToDoItem>> = object : Callback<List<ToDoItem>> {
                override fun onResponse(
                    call: Call<List<ToDoItem>>,
                    response: Response<List<ToDoItem>>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()
                        data?.let {
                            Toast.makeText(this@MainActivity,
                                "Number of results : "+ data.size,
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    else {
                        Log.d("xxx","An error occured with the http "+response.code())
                    }
                }

                override fun onFailure(call: Call<List<ToDoItem>>, t: Throwable) {
                    Log.d("xxx","An error occured in WS")
                }
            }

            service.getAllTodoItems().enqueue(callback)
        }
    }

}