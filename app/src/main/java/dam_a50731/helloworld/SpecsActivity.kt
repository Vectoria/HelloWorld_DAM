package dam_a50731.helloworld

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val CHANNEL_ID = "channelID"

class SpecsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.specs)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViewInfo = findViewById<TextView>(R.id.specs_text)

        val systemInfo = """
            Manufacturer: ${Build.MANUFACTURER}
            Model: ${Build.MODEL}
            Brand: ${Build.BRAND}
            Type: ${Build.TYPE}
            User: ${Build.USER}
            Base: ${Build.VERSION_CODES.BASE}
            Incremental: ${Build.VERSION.INCREMENTAL}
            SDK: ${Build.VERSION.SDK_INT}
            Version Code: ${Build.VERSION.RELEASE}
            Display: ${Build.DISPLAY}
        """.trimIndent()


        textViewInfo.text = systemInfo

        val imageView = findViewById<ImageView>(R.id.settings_icon)

        // Aplica a animação de rotação
        val rotateAnimation: Animation? =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotation)
        imageView?.startAnimation(rotateAnimation)


        // fazer notificação
        // do vídeo indiano: https://youtu.be/Kan_5OeSBN0?si=9u_9M7qNv3it8FNc
        createNotificationChannel()

        // video do youtube
        // https://youtu.be/71juUqkBGY4?si=pxSPZzyvHKMS9bj7
        val video = """
            <html style="margin:0; padding:0;">
                <body style="margin:0; padding:0;">
                    <iframe 
                        width="100%" 
                        height="100%" 
                        src="https://www.youtube.com/embed/k9iYm9PEAHg?si=mGj4ab4XuNoo0lEp" 
                        frameborder="0" 
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
                        referrerpolicy="strict-origin-when-cross-origin" 
                        allowfullscreen>
                    </iframe>
                </body>
            </html>
        """.trimIndent()
        val webView: WebView = findViewById<WebView>(R.id.webView)
        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()


    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "First channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Test description for my channel"
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun sendNotification(view: View) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notificação de Teste")
            .setContentText("Clicaste no botão e disparaste esta notificação!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Opcional: solicitar permissão antes de sair
                return
            }

            notify(1, builder.build())
        }
    }


}