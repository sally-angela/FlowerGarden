package com.sally.flowergarden.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.FragmentDetailBinding
import com.sally.flowergarden.viewmodel.FlowerDetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: FlowerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val flowerId = DetailFragmentArgs.fromBundle(requireArguments()).flowerId
            viewModel = ViewModelProvider(this).get(FlowerDetailViewModel::class.java)
            viewModel.fetch(flowerId)

            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.flowerLD.observe(viewLifecycleOwner, Observer {
            binding.txtTitleDetail.setText(viewModel.flowerLD.value?.title)
            binding.txtAuthorDetail.setText(viewModel.flowerLD.value?.author)
            binding.txtParagraph.setText(viewModel.flowerLD.value?.paragraphs?.first())

            var url = viewModel.flowerLD.value?.images
            val builder = Picasso.Builder(requireContext())
            builder.listener { picasso, uri, exception ->
                exception.printStackTrace() }
            builder.build().load(url).into(binding.imgFlowerDetail)
        })
    }
}