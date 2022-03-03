package com.example.contentresolver

import android.net.Uri
import android.provider.MediaStore

class Music(id: String, title:String?, artist: String?, albumId: String?, duration:Long?) {
    var id: String = "" // 음원 자체의 ID
    var title: String? = ""
    var artist: String? = ""
    var albumID: String? = "" //앨범 이미지 ID
    var duration: Long? = 0

    init {
        this.id = id
        this.title = title
        this.artist = artist
        this.albumID = albumId
        this.duration = duration
    }

    fun getMusicUri(): Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }

    fun getAlbumUri(): Uri{
        return Uri.parse("content://media/external/audio/albumart/$albumID")
    }
}