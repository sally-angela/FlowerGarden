package com.sally.flowergarden.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sally.flowergarden.databinding.FragmentHomeCardBinding
import com.sally.flowergarden.model.Flower
import com.squareup.picasso.Picasso

class HomeAdapter (val flowerList:ArrayList<Flower>)
    : RecyclerView.Adapter<HomeAdapter.FlowerViewHolder>() {
    class FlowerViewHolder(var binding: FragmentHomeCardBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val binding = FragmentHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlowerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.binding.txtTitle.text = flowerList[position].title
        holder.binding.txtAuthor.text = flowerList[position].author
        holder.binding.txtDesc.text = flowerList[position].description

        var url = flowerList[position].images
        val builder = Picasso.Builder(holder.binding.root.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(url).into(holder.binding.imgFlower)

        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateFlowerList(newFlowerList:ArrayList<Flower>) {
        flowerList.clear()
        flowerList.addAll(newFlowerList)
        notifyDataSetChanged()
    }
}
