package com.wukangjie.tablescrollview.special

import java.util.ArrayList


class Entity {
    var leftTitle: String? = null
        get() = if (field == null) "" else field
    var rightDatas: List<String>? = null
        get() = if (field == null) {
            ArrayList()
        } else field
}
