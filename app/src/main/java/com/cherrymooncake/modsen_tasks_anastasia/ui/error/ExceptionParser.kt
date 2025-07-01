package com.cherrymooncake.modsen_tasks_anastasia.ui.error

import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel

fun ExceptionDomainModel.parseToString() = when (this) {
    is ExceptionDomainModel.Other -> R.string.other_exception_text
    is ExceptionDomainModel.NoInternet -> R.string.no_internet_exception_text
}