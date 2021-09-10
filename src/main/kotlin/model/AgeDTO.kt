package model

data class AgeDTO(val value:Int) {
    companion object {
        internal val DEFAULT = AgeDTO(-1)
    }
}
