package model

import utils.CRCExtension.checkCRC
import utils.CRCExtension.toCRC
import utils.CipherUtil.decodeCipher
import utils.CipherUtil.encodeCipher
import java.nio.ByteBuffer

class Packet {

    private var clientId: Byte
    private var packetId: Long
    private var message: Message

    constructor(clientId: Byte, packetId: Long, message: Message) {
        this.clientId = clientId
        this.packetId = packetId
        this.message = message
    }

    constructor(byteArray: ByteArray) {
        val byteBuffer: ByteBuffer = ByteBuffer.wrap(byteArray)

        val firstByte = byteBuffer.get() // 1 byte
        checkFirstCode(byte = firstByte)

        val clientId = byteBuffer.get() // 1 byte
        val packetId = byteBuffer.long // 8 byte
        val packetLen = byteBuffer.int // 4 byte

        val crcPacket = byteBuffer.short // 2 byte
        val headerBytes = byteArray.copyOfRange(0, SIZE_HEADER)
        checkCRC(crc = crcPacket, byteArray = headerBytes)

        val messageBytes = ByteArray(packetLen) {
            byteBuffer.get()
        } // 16 + packetLen byte
        val message = Message(byteArray = messageBytes.decodeCipher())

        val crcMessage = byteBuffer.short // // 16 + packetLen byte + 2
        checkCRC(crc = crcMessage, byteArray = messageBytes)

        this.clientId = clientId
        this.packetId = packetId
        this.message = message
    }

    private fun checkFirstCode(byte: Byte) {
        if (byte.compareTo(MAGIC_BYTE) != 0) throw RuntimeException("First byte not valid")
    }

    fun convertToBytes(): ByteArray {
        val messageBytes = this.message.convertToBytes().encodeCipher()
        val byteBuffer = ByteBuffer.allocate(1 + 1 + 8 + 4 + 2 + messageBytes.size + 2)

        byteBuffer.apply {
            put(MAGIC_BYTE) // 1 byte
            put(clientId) // 1 byte
            putLong(packetId) // 8 byte
            putInt(messageBytes.size) // 4 byte
        }

        val headerBytes = byteBuffer.array().copyOfRange(0, SIZE_HEADER)

        byteBuffer.apply {
            putShort(headerBytes.toCRC())
            put(messageBytes)
            putShort(messageBytes.toCRC())
        }

        return byteBuffer.array()
    }

    override fun toString(): String {
        return "Packet(clientId=$clientId, packetId=$packetId, message=$message)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Packet

        if (clientId != other.clientId) return false
        if (packetId != other.packetId) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = clientId.toInt()
        result = 31 * result + packetId.hashCode()
        result = 31 * result + message.hashCode()
        return result
    }

    companion object {
        const val MAGIC_BYTE: Byte = 0x13
        const val SIZE_HEADER = 14
    }

    fun getClientId() = clientId
    fun getPacketId() = packetId
    fun getMessage() = message
}
