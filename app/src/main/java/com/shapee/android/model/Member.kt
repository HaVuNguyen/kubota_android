package com.shapee.android.model

data class Member(var id: String, var name: String, var place: String, var memberShip: Int = 0) {

    companion object {
        const val NORMAL = 0
        const val MEMBER = 2
    }
}
