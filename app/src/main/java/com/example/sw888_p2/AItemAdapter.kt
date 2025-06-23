package com.example.sw888_p2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.sw888_p2.databinding.ListItemLayoutBinding

class AItemAdapter(context: Context, private val items: List<AItem>) :
    ArrayAdapter<AItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ListItemLayoutBinding
        val view: View

        if (convertView == null) {
            binding = ListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ListItemLayoutBinding
        }

        val currentItem = items[position]

        binding.textViewTitle.text = currentItem.title
        binding.textViewSubtitle.text = currentItem.subtitle

        if (currentItem.imageResId != null) {
            binding.imageViewItem.setImageResource(currentItem.imageResId)
        } else {
            binding.imageViewItem.setImageResource(R.drawable.soon)
        }
        return view
    }
}