package com.bryanrady.lib_network.errorhandler

import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 22:29
 *    desc   :
 */
class ExceptionHandle {

    companion object {

        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504

        /**
         * 统一错误处理
         * @param e
         * @return
         */
        fun handleException(e: Throwable?): ResponseException? {
            val ex: ResponseException
            if (e is HttpException) {
                ex = ResponseException(e, Error.HTTP_ERROR)
                when (e.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message = "网络错误"
                    else -> ex.message = "网络错误"
                }
            } else if (e is ServerException) {
                //app的错误处理
                ex = ResponseException(e, e.code)
                ex.message = e.message
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {
                ex = ResponseException(e, Error.PARSE_ERROR)
                ex.message = "解析错误"
            } else if (e is ConnectException) {
                ex = ResponseException(e, Error.NETWORK_ERROR)
                ex.message = "连接错误"
            } else if (e is SSLHandshakeException) {
                ex = ResponseException(e, Error.SSL_ERROR)
                ex.message = "证书错误"
            } else if (e is TimeoutException
                || e is SocketTimeoutException) {
                ex = ResponseException(e, Error.TIMEOUT_ERROR)
                ex.message = "连接超时错误"
            } else if (e is UnknownHostException) {
                ex = ResponseException(e, Error.UNKNOWN_HOST_ERROR)
                ex.message = "未知域名错误"
            } else {
                ex = ResponseException(e, Error.UNKNOWN)
                ex.message = "未知错误"
            }
            return ex
        }
    }

    class ServerException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }

    /**
     * 给上层的统一错误响应
     */
    class ResponseException(e: Throwable?, private var code: Int) : Exception(e) {

        override var message: String? = null

        fun getCode(): Int {
            return code
        }

    }

    /**
     * 约定异常
     */
    object Error {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001

        /**
         * 网络连接错误
         */
        const val NETWORK_ERROR = 1002

        /**
         * http错误
         */
        const val HTTP_ERROR = 1003

        /**
         * 证书检验错误
         */
        const val SSL_ERROR = 1004

        /**
         * 连接超时错误
         */
        const val TIMEOUT_ERROR = 1005

        /**
         * 未知域名错误
         */
        const val UNKNOWN_HOST_ERROR = 1006
    }

}