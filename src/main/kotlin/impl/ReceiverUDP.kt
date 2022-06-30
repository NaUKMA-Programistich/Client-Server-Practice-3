package impl

import api.Receiver
import java.net.DatagramPacket

class ReceiverUDP(
    val datagramPacket: DatagramPacket
) : Receiver {
    override fun receiveMessage() {
        Thread {
            val inputBytes = datagramPacket.data
            println("Input-bytes:${inputBytes.contentToString()}")
            val inputPacket = DescriptorImpl().description(inputBytes)
            println("Input-packet:$inputPacket")
            val outputPacket = ProcessorImpl().process(inputPacket)
            println("Output-packet:$outputPacket")
            val outputBytes = EncryptorImpl().encrypt(outputPacket)
            println("Output-bytes:${inputBytes.contentToString()}")
            try {
                SenderImpl(outputBytes).send(datagramPacket.address, datagramPacket.port)
            } catch (exception: Exception) {
                //exception.printStackTrace()
            }
        }.start()
    }
}
