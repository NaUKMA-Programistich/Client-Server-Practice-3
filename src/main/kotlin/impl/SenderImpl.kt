package impl

import api.Sender
import network.udp.serverSocket
import java.io.ObjectOutputStream
import java.net.DatagramPacket
import java.net.InetAddress

class SenderImpl(
    private val outputBytes: ByteArray
) : Sender {

    override fun send(output: ObjectOutputStream) {
        output.writeObject(outputBytes)
        output.flush()
    }

    override fun send(address: InetAddress, port: Int) {
        val sendPacket = DatagramPacket(outputBytes, outputBytes.size, address, port)
        serverSocket.send(sendPacket)
    }
}
