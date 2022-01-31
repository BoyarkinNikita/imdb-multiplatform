package com.example.imdb.multiplatform.res.strings

import kotlin.LazyThreadSafetyMode.PUBLICATION

class StringsRu private constructor() : Strings() {

    companion object {
        val instance by lazy(PUBLICATION) { StringsRu() }
    }
}