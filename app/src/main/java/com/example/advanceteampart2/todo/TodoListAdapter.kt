package com.example.advanceteampart2.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advanceteampart2.databinding.TodoItemBinding

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val list = ArrayList<TodoModel>()

    fun addItems(items: List<TodoModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
        Log.d("Useong", "change")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: TodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoModel) = with(binding) {
            title.text = item.title
            comment.text = item.comment
        }

    }

}