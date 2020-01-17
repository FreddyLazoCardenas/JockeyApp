package com.example.domain.usecase

abstract class BaseUseCase<T> {
    @Throws(Exception::class)
    abstract fun execute(): T
}