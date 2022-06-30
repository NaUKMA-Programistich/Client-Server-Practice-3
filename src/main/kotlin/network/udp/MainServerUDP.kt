package network.udp

import impl.ReceiverUDP
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.util.concurrent.ArrayBlockingQueue

const val SERVER_PORT = 8000
var queue = ArrayBlockingQueue<DatagramPacket>(20)
val serverSocket = DatagramSocket(SERVER_PORT)

fun main() {
    println("Start server UDP")

    val output = ByteArray(1024)

    while (true) {
        val packet = DatagramPacket(output, output.size)
        serverSocket.receive(packet)

        val receiver = ReceiverUDP(packet)
        receiver.receiveMessage()
        queue.put(packet)
    }
}
