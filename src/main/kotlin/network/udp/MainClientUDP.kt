package network.udp

import model.Packet
import utils.Mock
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    Mock.bytes.forEach {
        val client = DatagramSocket()

        val outputBytes = ByteArray(1024)

        val sendPacket = DatagramPacket(it, it.size, InetAddress.getByName("localhost"), SERVER_PORT)

        while (true) {
            client.send(sendPacket)

            val output = DatagramPacket(outputBytes, outputBytes.size)
            client.receive(output)

            val packet = Packet(output.data)
            println(packet)
            break
        }
        client.close()
    }
}
