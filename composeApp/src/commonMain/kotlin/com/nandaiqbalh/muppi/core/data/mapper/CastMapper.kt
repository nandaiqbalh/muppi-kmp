package com.nandaiqbalh.muppi.core.data.mapper

import com.nandaiqbalh.muppi.core.data.dto.CastDetailDto
import com.nandaiqbalh.muppi.core.data.dto.CreditsDto
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.core.utils.orZero

fun CreditsDto.toCasts(): List<Cast> {
	return this.cast?.map { castDto ->
		Cast(
			id = castDto.id.orZero(),
			name = castDto.name.orEmpty(),
			character = castDto.character.orEmpty(),
			profilePath = castDto.profilePath.orEmpty()
		)
	} ?: emptyList()
}

fun CastDetailDto.toCastDetail(): CastDetail {
	return CastDetail(
		name = this.name.orEmpty(),
		alsoKnownAs = this.alsoKnownAs ?: emptyList(),
		birthday = this.birthday.orEmpty(),
		placeOfBirth = this.placeOfBirth.orEmpty(),
		id = this.id,
		biography = this.biography.orEmpty(),
		profilePath = this.profilePath.orEmpty(),
		adult = this.adult,
		knownForDepartment = this.knownForDepartment.orEmpty()
	)
}