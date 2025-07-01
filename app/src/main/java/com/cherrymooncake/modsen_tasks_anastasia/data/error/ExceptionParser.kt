package com.cherrymooncake.modsen_tasks_anastasia.data.error

import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import java.net.ConnectException
import java.net.UnknownHostException

fun Throwable.toExceptionDomainModel(): ExceptionDomainModel {
    return when (this){
        is UnknownHostException, is ConnectException -> ExceptionDomainModel.NoInternet(this)
        is ExceptionDomainModel -> this
        else -> ExceptionDomainModel.Other(this)
    }
}
