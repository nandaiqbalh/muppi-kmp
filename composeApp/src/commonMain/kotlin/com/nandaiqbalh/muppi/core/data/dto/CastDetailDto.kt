package com.nandaiqbalh.muppi.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDetailDto(
    @SerialName("adult")
    val adult: Boolean,

    @SerialName("also_known_as")
    val alsoKnownAs: List<String>? = null,

    @SerialName("biography")
    val biography: String? = null,

    @SerialName("birthday")
    val birthday: String? = null,

    @SerialName("id")
    val id: Int,

    @SerialName("known_for_department")
    val knownForDepartment: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("place_of_birth")
    val placeOfBirth: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null
)
