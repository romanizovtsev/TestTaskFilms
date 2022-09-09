package com.example.testfilms.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testfilms.App
import com.example.testfilms.databinding.FilmsFragmentBinding
import com.example.testfilms.model.RecyclerAdapter
import com.example.testfilms.viewmodel.FilmsViewModel
import com.example.testfilms.viewmodel.FilmsViewModelFactory
import javax.inject.Inject


class FilmsFragment : Fragment() {

    private lateinit var binding: FilmsFragmentBinding
    private lateinit var viewModel: FilmsViewModel
    private lateinit var adapter: RecyclerAdapter

    @Inject
    lateinit var filmsViewModelFactory: FilmsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilmsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapter(requireActivity())
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this, filmsViewModelFactory)[FilmsViewModel::class.java]
        viewModel.filmsLiveData.observe(requireActivity()) {
            adapter.setMovieList(it)
        }
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as App).applicationComponent.inject(this)
        super.onAttach(context)
    }
}