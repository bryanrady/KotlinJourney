package com.bryanrady.kotlinbase.variable

class User {

    var name: String = ""
    get() {
        return "LS$field"
    }
    set(value) {
        field = "WQB$value"
    }

    private var age: Int = 0



}