package com.example.sw888_p2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sw888_p2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ITEM = "com.example.sw888_p2.EXTRA_ITEM"
        const val RETURN_MESSAGE = "com.example.sw888_p2.RETURN_MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item: AItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ITEM, AItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        item?.let {
            binding.textViewDetailTitle.text = it.title
            binding.textViewDetailSubtitle.text = it.subtitle
            binding.textViewDetailDescription.text = it.description

            if (it.imageResId != null) { // Use imageResId
                binding.imageViewDetail.setImageResource(it.imageResId)
            } else {
                binding.imageViewDetail.setImageResource(R.mipmap.ic_launcher) // Default or placeholder
            }
            val resultIntent = Intent().apply {
                putExtra(RETURN_MESSAGE, it.title) // Pass back the title for the Toast/Snackbar
            }
            setResult(RESULT_OK, resultIntent)
        } ?: run {
            binding.textViewDetailTitle.text = "Error: Item not found"
            setResult(RESULT_CANCELED)
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressedDispatcher.onBackPressed()
        return true
    }
}