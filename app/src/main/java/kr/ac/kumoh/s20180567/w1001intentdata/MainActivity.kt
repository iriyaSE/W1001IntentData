package kr.ac.kumoh.s20180567.w1001intentdata

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20180567.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    companion object {
        const val keyName = "image"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGundam.setOnClickListener(this)
        binding.btnZaku.setOnClickListener(this)

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK) {
                return@registerForActivityResult
            }
            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NONE)

            val str = when (result) {
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }

            val image = it.data?.getStringExtra(ImageActivity.imageName)
            when (image) {
                "gundam" -> binding.btnGundam.text = "건담 ($str)"
                "zaku" -> binding.btnZaku.text = "자쿠 ($str)"
            }
        }

    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when (v?.id) {
            binding.btnGundam.id -> "gundam"
            binding.btnZaku.id -> "zaku"
            else -> return
        }
//        binding.btnGundam.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            intent.putExtra("image", "gundam")
//            startActivity(intent)
//        }
//        binding.btnZaku.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            intent.putExtra("image", "zaku")
//            startActivity(intent)
//        }
        intent.putExtra(keyName, value)
//        startActivity(intent)
        launcher.launch(intent)
    }
}