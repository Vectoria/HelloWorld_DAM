package dam_a50731.helloworld

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.animation.AnimationUtils

class SpecsActivity : AppCompatActivity() {
    lateinit var rotateAnimation : Animation
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.specs)

        val textViewInfo = findViewById<TextView>(R.id.specs_text)

        val systemInfo = """
            Manufacturer: ${Build.MANUFACTURER}
            Model: ${Build.MODEL}
            Device: ${Build.DEVICE}
            Brand: ${Build.BRAND}
            Board: ${Build.BOARD}
            Hardware: ${Build.HARDWARE}
            Bootloader: ${Build.BOOTLOADER}
            Android Version: ${Build.VERSION.RELEASE}
            Base: ${Build.VERSION_CODES.BASE}
            Incremental: ${Build.VERSION.INCREMENTAL}
            SDK: ${Build.VERSION.SDK_INT}
            API Level: ${Build.VERSION.SDK_INT}
            Build ID: ${Build.ID}
            Build Type: ${Build.TYPE}
            User: ${Build.USER}
            Fingerprint: ${Build.FINGERPRINT}
            Display: ${Build.DISPLAY}
        """.trimIndent()

        textViewInfo.text = systemInfo

        val imageView = findViewById<ImageView>(R.id.settings_icon)

        // Aplica a animação de rotação
        val rotateAnimation: Animation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotation)
        imageView.startAnimation(rotateAnimation)
    }


}