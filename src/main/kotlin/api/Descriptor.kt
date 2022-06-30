package api

import model.Packet

interface Descriptor {
    fun description(byteArray: ByteArray): Packet
}
