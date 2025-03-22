package com.nandaiqbalh.muppi.core.domain.model

data class CastDetail(
	val adult: Boolean,
	val alsoKnownAs: List<String>,
	val biography: String,
	val birthday: String,
	val id: Int,
	val knownForDepartment: String,
	val name: String,
	val placeOfBirth: String,
	val profilePath: String
)
