package com.nandaiqbalh.muppi.core.data.mapper

import com.nandaiqbalh.muppi.core.data.dto.VideosDto
import com.nandaiqbalh.muppi.core.domain.model.Video
import com.nandaiqbalh.muppi.core.utils.orFalse
import com.nandaiqbalh.muppi.core.utils.orZero

fun VideosDto.toVideos(): List<Video> {
	return this.results.map { result ->
		Video(
			name = result.name.orEmpty(),
			key = result.key.orEmpty(),
			site = result.site.orEmpty(),
			size = result.size.orZero(),
			type = result.type.orEmpty(),
			official = result.official.orFalse(),
			id = result.id.orEmpty()
		)
	}
}