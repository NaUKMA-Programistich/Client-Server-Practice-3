package impl

import api.Receiver
import java.io.ObjectOutputStream

class ReceiverTCP(
    private val inputBytes: ByteArray,
    private val output: ObjectOutputStream,
) : Receiver {
    override fun receiveMessage() {
        Thread {
            println("Input-bytes:${inputBytes.contentToString()}")
            val inputPacket = DescriptorImpl().description(inputBytes)
            println("Input-packet:$inputPacket")
            val outputPacket = ProcessorImpl().process(inputPacket)
            println("Output-packet:$outputPacket")
            val outputBytes = EncryptorImpl().encrypt(outputPacket)
            println("Output-bytes:${inputBytes.contentToString()}")
            try {
                SenderImpl(outputBytes).send(output)
            } catch (exception: Exception) {
                //exception.printStackTrace()
            }
        }.start()
    }
}
