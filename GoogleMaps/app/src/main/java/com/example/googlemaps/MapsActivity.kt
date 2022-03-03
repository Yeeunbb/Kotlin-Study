package com.example.googlemaps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.googlemaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
        val hanam = LatLng(37.3221, 127.1253)
        // 마커 아이콘 만들기
        val descriptor = getDescriptorFromDrawable(R.drawable.choco)

        // Marker
        val marker = MarkerOptions()
            .position(hanam)
            .title("Marker in Hanam")
            .icon(descriptor)
        mMap.addMarker(marker)
//        mMap.addMarker(MarkerOptions().position(hanam).title("Marker in Hanam"))

        // Camera on marker
//        val camera = CameraUpdateFactory.newLatLng(hanam)
        val zoomCameraOption = CameraPosition.Builder()
            .target(hanam)
            .zoom(12f) //숫자가 높아질 수록 줌 인,
            .build()
        val camera2 = CameraUpdateFactory.newCameraPosition(zoomCameraOption)
        mMap.moveCamera(camera2)
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(hanam))
    }

    fun getDescriptorFromDrawable(drawableId : Int) : BitmapDescriptor {
        var bitmapDrawable: BitmapDrawable
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            bitmapDrawable = getDrawable(drawableId) as BitmapDrawable
        }else{
            bitmapDrawable = resources.getDrawable(drawableId) as BitmapDrawable
        }

        //마커 크기 변환
        val scaledBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 200, 200, false)
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }

}