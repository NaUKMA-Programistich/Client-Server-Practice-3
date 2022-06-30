package utils

import model.Message
import model.Packet
import request.*

object Mock {

    val messageOne = Message(
        commandId = 1,
        userId = 2,
        data = GetProductsRequest(
            nameProduct = "apple"
        )
    )

    val messageTwo = Message(
        commandId = 2,
        userId = 2,
        data = DeleteProductRequest(
            nameProduct = "apple",
            count = 4
        )
    )

    val messageThree = Message(
        commandId = 3,
        userId = 2,
        data = AddProductRequest(
            nameProduct = "apple",
            count = 10
        )
    )

    val messageFour = Message(
        commandId = 4,
        userId = 4,
        data = AddGroupRequest(
            nameGroup = "it"
        )
    )

    val messageFive = Message(
        commandId = 5,
        userId = 5,
        data = AddProductToGroupRequest(
            nameProduct = "apple",
            nameGroup = "it"
        )
    )

    val messageSix = Message(
        commandId = 6,
        userId = 6,
        data = SetProductPriceRequest(
            nameProduct = "apple",
            price = 23.5
        )
    )

    val messages = listOf(
        messageOne,
        messageTwo,
        messageThree,
        messageFour,
        messageFive,
        messageSix,
    )

    val packets = messages.mapIndexed { index, message ->
        Packet(
            clientId = 1,
            packetId = index.toLong() + 1,
            message = message
        )
    }
    val packet = packets.first()

    val bytes = packets.map { it.convertToBytes() }
    val byte = bytes.first()
}
