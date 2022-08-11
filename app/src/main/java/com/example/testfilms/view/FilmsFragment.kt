package com.example.testfilms.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testfilms.viewmodel.FilmsViewModel
import com.example.testfilms.databinding.FilmsFragmentBinding
import com.example.testfilms.model.RecyclerAdapter
import com.example.testfilms.model.Repository
import com.example.testfilms.model.RetrofitService
import com.example.testfilms.viewmodel.FilmsViewModelFactory


class FilmsFragment : Fragment() {

    private lateinit var binding: FilmsFragmentBinding
    lateinit var viewModel: FilmsViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilmsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapter(requireActivity())
        viewModel = ViewModelProvider(this, FilmsViewModelFactory(Repository(retrofitService))).get(
            FilmsViewModel::class.java
        )
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getFilms()
    }


}