package com.example.coroutine

import java.io.Serializable

class BaseResponse<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    var data: T
): Serializable

fun <T> BaseResponse<T>.dataConvert(): T {
    if (errorCode == 0) {
        return data
    } else {
        throw Exception(errorMsg)
    }
}