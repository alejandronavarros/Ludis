package com.example.nombre

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.nombre.databinding.ActivityMapsBinding
import com.google.android.gms.maps.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lista: List<MarkerOptions>
    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        geocoder = Geocoder(baseContext, Locale.getDefault())
        binding.botonMapa.setOnClickListener {
            val intent = Intent(this , MenuFragment::class.java)
            startActivity(intent)
        }
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
        if(!mMap.uiSettings.isMyLocationButtonEnabled)
            mMap.uiSettings.isMyLocationButtonEnabled = true
        if(!mMap.uiSettings.isCompassEnabled)
            mMap.uiSettings.isCompassEnabled = true
        if(!mMap.uiSettings.isZoomControlsEnabled)
            mMap.uiSettings.isZoomControlsEnabled = true


        val casa = LatLng(37.6183584,-0.9894975)
        val chino = LatLng(37.609937, -0.988582)
        val random = LatLng(37.603338, -0.981762)
        lista = listOf(MarkerOptions().position(casa),MarkerOptions().position(chino),MarkerOptions().position(random))

        crearMarcas(mMap)
        mMap.setOnMapClickListener { latLng ->
            mMap.clear()
            crearMarcas(mMap)
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            val location = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(location))
            val address = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1)
            binding.botonMapa.visibility = View.VISIBLE
            if(address.size > 0) {
                Log.d("Map", "Address: ${address.get(0).getAddressLine(0)}")
                Log.d("Map", "Ciudad: ${address.get(0).locality}")
                Log.d("Map", "Comunidad: ${address.get(0).adminArea}")
            }else {
                Log.d("Map", "Latitud: ${latLng.latitude}")
                Log.d("Map", "Longitud: ${latLng.longitude}")
            }
        }
    }
    private fun crearMarcas(mMap: GoogleMap){
        for (l in lista) {
            mMap.addMarker(l)
        }
    }
}