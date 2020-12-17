package com.example.profilewithlocation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val success: List<SuccessItem1?>? = null,
	val location: List<LocationItem1?>? = null

					) : Parcelable

@Parcelize
data class SuccessItem1(
	val image: String? = null,
	val address: String? = null,
	val contact: String? = null,
	val name: String? = null,
	val description: String? = null,
	val id: String? = null,
	val empcode: String? = null,
	val category: String? = null,
	val categoryid: String? = null
) : Parcelable

@Parcelize
data class LocationItem1(
	val longg: String? = null,
	val lat: String? = null
) : Parcelable
