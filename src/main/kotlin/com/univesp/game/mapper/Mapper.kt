package com.univesp.game.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}