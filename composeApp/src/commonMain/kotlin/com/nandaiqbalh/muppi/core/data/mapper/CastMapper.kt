package com.nandaiqbalh.muppi.core.data.mapper

import com.nandaiqbalh.muppi.core.data.dto.CreditsDto
import com.nandaiqbalh.muppi.core.domain.model.Cast
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