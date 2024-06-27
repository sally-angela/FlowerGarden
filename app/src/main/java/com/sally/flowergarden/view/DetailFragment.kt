package com.sally.flowergarden.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.FragmentDetailBinding
import com.sally.flowergarden.model.Flower
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
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {

            binding.flower = Flower("", "", "", "https://randomuser.me/api/portraits/flower/70.jpg")
            val flowerId = DetailFragmentArgs.fromBundle(requireArguments()).flowerId

            viewModel = ViewModelProvider(this).get(FlowerDetailViewModel::class.java)
            viewModel.fetch(flowerId)

            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.flowerLD.observe(viewLifecycleOwner, Observer {
            binding.flower = it
//            var paragraphCount = viewModel.flowerLD.value?.paragraphs?.size ?: 0
//            var currentParagraph = 0;
//
//            binding.txtTitleDetail.setText(viewModel.flowerLD.value?.title)
//            binding.txtAuthorDetail.setText(viewModel.flowerLD.value?.author)
//            if(paragraphCount > 0) {
//                binding.txtParagraph.setText(viewModel.flowerLD.value?.paragraphs?.first())
//                currentParagraph = 1;
//                binding.btnPrev.visibility = View.GONE
//            }
//            else {
//                binding.txtParagraph.setText("This article has no paragraph.")
//            }
//
//            var url = viewModel.flowerLD.value?.images
//            val builder = Picasso.Builder(requireContext())
//            builder.listener { picasso, uri, exception ->
//                exception.printStackTrace() }
//            builder.build().load(url).into(binding.imgFlowerDetail)
//
//            binding.btnPrev.setOnClickListener {
//                if(currentParagraph > 1) {
//                    binding.txtParagraph.setText(viewModel.flowerLD.value?.paragraphs?.get(currentParagraph-2))
//                    currentParagraph--
//
//                    if(currentParagraph == 1) {
//                        binding.btnPrev.visibility = View.GONE
//                    }
//                    if(currentParagraph > 1){
//                        binding.btnNext.visibility = View.VISIBLE
//                    }
//                }
//                else {
//                    Toast.makeText(requireContext(), "This is the first paragraph", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            binding.btnNext.setOnClickListener {
//                if(currentParagraph < paragraphCount) {
//                    binding.txtParagraph.setText(viewModel.flowerLD.value?.paragraphs?.get(currentParagraph))
//                    currentParagraph++
//
//                    if(currentParagraph < paragraphCount) {
//                        binding.btnPrev.visibility = View.VISIBLE
//                    }
//                    if(currentParagraph == paragraphCount) {
//                        binding.btnNext.visibility = View.GONE
//                    }
//                }
//                else {
//                    Toast.makeText(requireContext(), "This is the last paragraph", Toast.LENGTH_SHORT).show()
//                }
//            }
        })
    }
}