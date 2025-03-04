package dam_a50731.helloworld

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SpecsActivity: AppCompatActivity() {
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
            API Level: ${Build.VERSION.SDK_INT}
            Build ID: ${Build.ID}
            Build Type: ${Build.TYPE}
            Fingerprint: ${Build.FINGERPRINT}
        """.trimIndent()

        textViewInfo.text = systemInfo
    }

}