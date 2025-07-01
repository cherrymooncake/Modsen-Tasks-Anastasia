package com.cherrymooncake.modsen_tasks_anastasia.domain.error

sealed class ExceptionDomainModel(exception: Throwable) : Throwable(exception) {
    override val cause: Throwable = exception

    class Other(exception: Throwable) : ExceptionDomainModel(exception)
    class NoInternet(exception: Throwable) : ExceptionDomainModel(exception)
}
