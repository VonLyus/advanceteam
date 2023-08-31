package com.example.advanceteampart2.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advanceteampart2.databinding.BookmarkFragmentBinding

class BookmarkFragment : Fragment() {

    companion object {
        //interface를 초기화하는 설정
        fun newInstance() = BookmarkFragment()
    }

    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        BookmarkListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        // for testww
        val testList = arrayListOf<BookmarkModel>()
        for (i in 0 until 10) {
            testList.add(
                BookmarkModel(
                    id = i,
                    "Bookmark Title $i"
                )
            )
        }

        listAdapter.addItems(testList)
    }

    private fun initView() = with(binding) {
        bookmarkList.adapter = listAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}