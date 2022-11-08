package kr.ac.kumoh.s20180567.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.ac.kumoh.s20180567.w1001intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityImageBinding
    private var image: String? = null
    companion object {
        const val imageName = "image"
        const val resultName = "result"
        const val LIKE = 10
        const val DISLIKE = 20
        const val NONE = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val res = when(intent.getStringExtra(MainActivity.keyName)) {
            "gundam" -> R.drawable.sea
            "zaku" -> R.drawable.tree
            else -> R.drawable.ic_launcher_background
        }
        binding.imgGundam.setImageResource(res)

        binding.btnLike.setOnClickListener(this)
        binding.btnDislike.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent = Intent()
        val value = when (v?.id) {
            binding.btnLike.id -> LIKE
            binding.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(imageName, image)
        intent.putExtra(resultName, value)
        setResult(RESULT_OK, intent)
        finish()
    }
}