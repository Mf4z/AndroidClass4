package fr.epita.adroidclass4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class ToDoListAdapter (val data : List<ToDoItem>, val context: Activity, val onItemClickListener : OnClickListener): Adapter<ToDoListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewList_Item)
        val statusImageView: ImageView = itemView.findViewById(R.id.imageView_item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowView : View = LayoutInflater.from(context).inflate(R.layout.list_todo,parent, false)
        rowView.setOnClickListener(onItemClickListener)
        val viewHolder = ViewHolder(rowView)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : ToDoItem = data[position]
        holder.titleTextView.text = item.title

        if(item.completed) {
            holder.statusImageView.setImageResource(android.R.drawable.arrow_up_float)
        }
        else {
            holder.statusImageView.setImageResource(android.R.drawable.arrow_down_float)
        }
        holder.itemView.tag = position
    }
}