package com.example.advanceteampart2.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advanceteampart2.databinding.TodoFragmentBinding
import com.example.advanceteampart2.main.MainActivity

class TodoFragment: Fragment() {

    private val testList = arrayListOf<TodoModel>()

    companion object {
        fun newInstance() = TodoFragment()
    }

    private var _binding: TodoFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        TodoListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        for (i in 0 until 10) {
            testList.add(
                TodoModel(
                    id = i,
                    "Todo Title $i",
                    "Todo Comment $i"
                )
            )
        }


        listAdapter.addItems(testList)

    }

    fun addTodoListSetting(title: String?,comment: String?){

        testList.add(
            TodoModel(
                id = testList.size + 1,
                title.toString(),
                comment.toString()
            )
        )
        Log.d("Useong", (testList.size + 1).toString())
        Log.d("Useong", title.toString())
        Log.d("Useong", comment.toString())

        listAdapter.addItems(testList)

    }
    
    private fun initView() = with(binding) {
        todoList.adapter = listAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}