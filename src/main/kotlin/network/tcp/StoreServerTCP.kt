package network.tcp

import impl.ReceiverTCP
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class StoreServerTCP(
    private val socket: Socket,
    private val input: ObjectInputStream = ObjectInputStream(socket.getInputStream()),
    private val output: ObjectOutputStream = ObjectOutputStream(socket.getOutputStream())
) : Thread() {

    override fun run() {
        try {
            var packet = ByteArray(0)
            while (true) {
                try {
                    println(packet)
                    packet = input.readObject() as ByteArray
                } catch (exception: IOException) {
                    println("Exception on read object")
                }
                try {
                    val receiverTCP = ReceiverTCP(packet, output)
                    receiverTCP.receiveMessage()
                } catch (exception: Exception) {
                }
            }
        } catch (exception: ClassNotFoundException) {
            println("ClassNotFoundException in socket TCP")
        } finally {
            socket.close()
            println("Close socket TCP")
        }
    }
}
