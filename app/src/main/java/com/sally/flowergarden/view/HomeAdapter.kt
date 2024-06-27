package com.sally.flowergarden.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sally.flowergarden.databinding.FragmentHomeCardBinding
import com.sally.flowergarden.model.Flower

class HomeAdapter (val flowerList:ArrayList<Flower>)
    : RecyclerView.Adapter<HomeAdapter.FlowerViewHolder>(), FlowerReadClickListener{
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
        holder.binding.flower = flowerList[position]
        holder.binding.readlistener = this

//        var url = flowerList[position].images
//        val builder = Picasso.Builder(holder.binding.root.context)
//        builder.listener { picasso, uri, exception ->
//            exception.printStackTrace() }
//        builder.build().load(url).into(holder.binding.imgFlower)
    }

    fun updateFlowerList(newFlowerList: List<Flower>) {
        flowerList.clear()
        flowerList.addAll(newFlowerList)
        notifyDataSetChanged()
    }

    override fun onFlowerReadClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = HomeFragmentDirections.actionDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}
