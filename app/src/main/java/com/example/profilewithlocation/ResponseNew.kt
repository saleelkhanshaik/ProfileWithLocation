package com.example.profilewithlocation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseNew(

	@field:SerializedName("location")
	val location: List<Location?>? = null,

	@field:SerializedName("Success")
	val success: List<Success?>? = null
) : Parcelable

@Parcelize
data class Success(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("empcode")
	val empcode: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("categoryid")
	val categoryid: String? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("longg")
	val longg: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
) : Parcelable
