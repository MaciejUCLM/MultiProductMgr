package com.mnpm.multiproductmgr

interface SortI {
    companion object {
        const val SORT_NAME = 0
        const val SORT_YEAR = 1
        const val SORT_TYPE = 2
    }

    fun sortElements(mode: Int)
}