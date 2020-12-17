package com.example.profilewithlocation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private var response: List<Success?>?=null
    private var addedmarker: ArrayList<Marker>?=null
    private var bmp: Bitmap?=null
    private var uiScope: CoroutineScope?=null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
         uiScope = CoroutineScope(Dispatchers.Main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        apiCall()

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
        mMap.setOnMarkerClickListener(this)
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    fun apiCall(){
        val profile = RetrofitAPI.instance.getProfileDetails()
        profile.enqueue(object : Callback<ResponseNew> {
            override fun onFailure(call: Call<ResponseNew>, t: Throwable) {
                Log.d("MAIN ACT" + call.request().url(), ""+t.toString())
            }
            override fun onResponse(
                    call: Call<ResponseNew>,
                    response: Response<ResponseNew>) {
                Log.d("MAIN ACT123----------" + call.request().url(), ""+ (response.body()?.location?.size?: "0000") +" -- "+
                    (response.body()?.success?.size?: "0000"))
                uiScope?.launch { addMarkers(response.body()) }
            }
        })
    }
    suspend fun addMarkers(body: ResponseNew?) {
        response = body?.success
        for(i in body?.location?.indices!!){
            val url = URL(body.success?.get(i)?.image!!)
           
            withContext(Dispatchers.IO){

                 bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bmp = Bitmap.createScaledBitmap(bmp as Bitmap, 72, 72, false);
            }
             var marker=  mMap.addMarker(MarkerOptions().position(LatLng(
                body.location[i]?.lat!!.toDouble(),
                body.location[i]?.longg!!.toDouble())).title("").anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp)))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(
                body.location[i]?.lat!!.toDouble(),
                body.location[i]?.longg!!.toDouble())))
            marker.tag=i
            //addedmarker?.get(i)?.tag = i

        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        val position:Int = p0?.tag as Int
        Log.d("onMarkerClick", "onMarkerClick: $position")
        val intent = Intent(this,UserDetails::class.java)
        intent.putExtra("userDetails", response?.get(position))
        startActivity(intent)
        return false
    }

}