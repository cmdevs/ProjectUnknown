package com.cmdevs.projectunknown.domain.profile

import com.cmdevs.projectunknown.data.UserDataSource
import com.cmdevs.projectunknown.domain.UseCase

class ObserveProfileModifiedDateUseCase(
        val firebaseUserDataSource: UserDataSource
) : UseCase<String, Boolean>() {
    override fun execute(parameters: String) = firebaseUserDataSource.modifiedDate(parameters)
}