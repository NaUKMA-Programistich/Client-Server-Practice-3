package model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.ByteBuffer

class Message {
    private var commandId: Int
    private var userId: Int
    private var data: Any

    constructor(commandId: Int, userId: Int, data: Any) {
        this.commandId = commandId
        this.userId = userId
        this.data = data
    }

    constructor(byteArray: ByteArray) {
        val mapper = jacksonObjectMapper()
        val dataBytes = byteArray.copyOfRange(8, byteArray.size)

        val byteBuffer = ByteBuffer.wrap(byteArray)

        this.commandId = byteBuffer.int
        this.userId = byteBuffer.int
        this.data = mapper.readValue(dataBytes)
    }

    fun convertToBytes(): ByteArray {
        val mapper = jacksonObjectMapper()

        val informationBytes = mapper.writeValueAsBytes(data)
        val byteBuffer = ByteBuffer.allocate(4 + 4 + informationBytes.size)

        byteBuffer.apply {
            putInt(commandId)
            putInt(userId)
            put(informationBytes)
        }

        return byteBuffer.array()
    }

    override fun toString(): String {
        return "Message(commandId=$commandId, userId=$userId, data=$data)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (commandId != other.commandId) return false
        if (userId != other.userId) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = commandId
        result = 31 * result + userId
        result = 31 * result + data.hashCode()
        return result
    }

    fun getCommandId() = commandId
    fun getUserId() = userId
    fun getData() = data
}
