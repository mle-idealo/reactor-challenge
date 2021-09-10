package model

data class HairDTO(val value:Int) {
    companion object {
        internal val DEFAULT = HairDTO(-1)
    }
}