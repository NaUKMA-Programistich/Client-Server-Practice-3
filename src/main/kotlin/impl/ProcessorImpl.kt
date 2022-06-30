package impl

import api.Processor
import model.Message
import model.Packet
import response.ErrorResponse
import response.OkResponse

class ProcessorImpl : Processor {
    override fun process(inputPacket: Packet): Packet {
        return processPacket(inputPacket)
    }

    private fun processPacket(packet: Packet): Packet {
        val message = packet.getMessage()
        val newMessage = processMessage(message)
        return Packet(
            clientId = packet.getClientId(),
            packetId = packet.getPacketId(),
            message = newMessage
        )
    }

    private fun processMessage(message: Message): Message {
        return try {
            when (message.getCommandId()) {
                1 -> processCountOfProduct(message)
                2 -> processMinusProduct(message)
                3 -> processPlusProduct(message)
                4 -> processAddGroup(message)
                5 -> processAddProductToGroup(message)
                6 -> processPriceToProduct(message)
                else -> processError(message)
            }
        } catch (exception: Exception) {
            processError(message)
        }
    }

    override fun processError(message: Message): Message {
        return Message(
            commandId = 0,
            userId = message.getUserId(),
            data = ErrorResponse()
        )
    }

    override fun processCountOfProduct(message: Message): Message {
        return Message(
            commandId = 1,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }

    override fun processMinusProduct(message: Message): Message {
        return Message(
            commandId = 2,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }

    override fun processPlusProduct(message: Message): Message {
        return Message(
            commandId = 3,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }

    override fun processAddGroup(message: Message): Message {
        return Message(
            commandId = 4,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }

    override fun processAddProductToGroup(message: Message): Message {
        return Message(
            commandId = 5,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }

    override fun processPriceToProduct(message: Message): Message {
        return Message(
            commandId = 6,
            userId = message.getUserId(),
            data = OkResponse()
        )
    }
}
