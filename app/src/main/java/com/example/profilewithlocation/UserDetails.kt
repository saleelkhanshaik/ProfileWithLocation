package com.example.profilewithlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val bundle = intent.extras
        val oalInfo = bundle?.get("userDetails") as Success
        id.text = oalInfo.id
        name.text = oalInfo.name
        category.text = oalInfo.category
        description.text = oalInfo.description
        contact.text = oalInfo.contact
        empcode.text = oalInfo.empcode


        Glide
            .with(this)
            .load(oalInfo.image)
            .centerCrop()
            .into(profileImage);
    }
}