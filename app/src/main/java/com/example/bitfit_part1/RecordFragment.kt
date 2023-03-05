package com.example.bitfit_part1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecordFragment : Fragment() {
    private var items = mutableListOf<BitFitItem>()
    private lateinit var itemAdapter: BitFitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchItems()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_record, container, false)
        val layoutManager = LinearLayoutManager(context)
        val itemRecyclerView = view.findViewById<RecyclerView>(R.id.foods)
        itemRecyclerView.layoutManager = layoutManager
        itemRecyclerView.setHasFixedSize(true)
        itemAdapter = BitFitAdapter(items)
        itemRecyclerView.adapter = itemAdapter

        return view

    }
    private fun fetchItems() {
        lifecycleScope.launch {
            (activity?.application as BitFitApplication).db.bitFitDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    BitFitItem(
                        entity.itemName,
                        entity.calories
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    itemAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        fun newInstance() : RecordFragment{
           return  RecordFragment()
        }
    }
}