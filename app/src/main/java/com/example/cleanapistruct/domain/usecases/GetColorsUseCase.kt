package com.example.cleanapistruct.domain.usecases

import com.example.cleanapistruct.domain.repository.Repository
import javax.inject.Inject

class GetColorsUseCase @Inject constructor(private var repository: Repository) {
    // todo სად იყენებ ამ კლასის ობიექტს?

    suspend operator fun invoke() =repository.getColors()

}