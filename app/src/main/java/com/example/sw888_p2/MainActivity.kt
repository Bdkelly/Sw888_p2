package com.example.sw888_p2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sw888_p2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsAdapter: AItemAdapter
    private lateinit var itemsList: ArrayList<AItem>

    private val detailActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val returnedItemTitle = result.data?.getStringExtra(DetailActivity.RETURN_MESSAGE) ?: "Operation"

                Snackbar.make(binding.root, "$returnedItemTitle details viewed successfully!", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss") {}
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemsList = getSampleItems()
        itemsAdapter = AItemAdapter(this, itemsList)
        binding.listViewItems.adapter = itemsAdapter

        binding.listViewItems.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = itemsList[position]

            val itemDetailsForNotification = "Title: ${selectedItem.title}, Subtitle: ${selectedItem.subtitle}"
            Snackbar.make(view, itemDetailsForNotification, Snackbar.LENGTH_SHORT).show()

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_ITEM, selectedItem)
            }
            detailActivityLauncher.launch(intent)
        }
    }

    private fun getSampleItems(): ArrayList<AItem> {
        return arrayListOf(
            AItem(1, "Pizza", "$51.99", "We are not making you buy this",R.drawable.pizza),
            AItem(2, "Burger", "$7.99", "Could be better",R.drawable.burger),
            AItem(3, "Chicken", "$6.99", "The best chicken.",R.drawable.chicken),
            AItem(4, "French Fries", "$4.99", "Freedom Fries.",R.drawable.fries)
            // ... add more items
        )
        // Make sure you have 'item_image_1.png', 'item_image_2.png', 'item_image_3.png' in res/drawable
    }
}