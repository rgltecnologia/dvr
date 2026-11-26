package com.exemplo.camerasintelbras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.rtsp.RtspMediaSource
import androidx.media3.ui.PlayerView

class PlayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerView = findViewById(R.id.player_view)

        // Pega a URL passada pela MainActivity
        val rtspUrl = intent.getStringExtra("RTSP_URL") ?: return

        // Configura o player
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        // Cria a fonte RTSP
        val mediaSource = RtspMediaSource.Factory()
            .createMediaSource(MediaItem.fromUri(rtspUrl))
        
        player?.setMediaSource(mediaSource)
        player?.prepare()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}