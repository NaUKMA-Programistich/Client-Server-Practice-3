package api

import model.Message
import model.Packet

interface Processor {
    fun process(inputPacket: Packet): Packet
    fun processError(message: Message): Message
    fun processCountOfProduct(message: Message): Message
    fun processMinusProduct(message: Message): Message
    fun processPlusProduct(message: Message): Message
    fun processAddGroup(message: Message): Message
    fun processAddProductToGroup(message: Message): Message
    fun processPriceToProduct(message: Message): Message
}
