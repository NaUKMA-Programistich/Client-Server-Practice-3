package impl

import api.Encryptor
import model.Packet
import utils.QueueExtensions.takeIn
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class EncryptorImpl : Encryptor {

    override fun encrypt(outputPacket: Packet): ByteArray {
       return outputPacket.convertToBytes()
    }
}
