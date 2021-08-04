package com.bryanrady.lib_network.bean

import java.io.Serializable

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 23:09
 *    desc   :
 */
class BaseResponse<T> : Serializable {

    private var resultcode: String? = null
    private var reason: String? = null
    private var result: ResultBean<T>? = null
    private var error_code = 0

    fun getResultcode(): String? {
        return resultcode
    }

    fun setResultcode(resultcode: String?) {
        this.resultcode = resultcode
    }

    fun getReason(): String? {
        return reason
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun getResult(): ResultBean<T>? {
        return result
    }

    fun setResult(result: ResultBean<T>?) {
        this.result = result
    }

    fun getError_code(): Int {
        return error_code
    }

    fun setError_code(error_code: Int) {
        this.error_code = error_code
    }

    class ResultBean<T> {

        private var stat: String? = null
        private var data: T? = null
        private var page: String? = null
        private var pageSize: String? = null

        fun getStat(): String? {
            return stat
        }

        fun setStat(stat: String?) {
            this.stat = stat
        }

        fun getData(): T? {
            return data
        }

        fun setData(data: T) {
            this.data = data
        }

        fun getPage(): String? {
            return page
        }

        fun setPage(page: String?) {
            this.page = page
        }

        fun getPageSize(): String? {
            return pageSize
        }

        fun setPageSize(pageSize: String?) {
            this.pageSize = pageSize
        }

    }

}