package api

import model.Packet

interface Encryptor {
    fun encrypt(outputPacket: Packet): ByteArray
}
