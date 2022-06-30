package network.tcp

import model.Packet
import utils.Mock
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket

class StoreClientTCP(
    private val address: InetAddress
) : Thread() {
    companion object {
        var count = 0
        const val max = 1
    }

    private lateinit var socket: Socket
    private lateinit var output: ObjectOutputStream
    private lateinit var input: ObjectInputStream
    private val number = count + 1

    init {
        initial()
    }

    private fun initial() {
        count++
        println("Start client $number")
        for (i in 1 until max + 1) {
            try {
                socket = Socket(address, SERVER_PORT)
                break
            } catch (exception: IOException) {
                println("Server not found")
                sleep(1000)
                if (max == i) return
            }
        }
        try {
            output = ObjectOutputStream(socket.getOutputStream())
            input = ObjectInputStream(socket.getInputStream())
            this.start()
        } catch (exception: IOException) {
            println("Client close")
            try {
                socket.close()
            } catch (exceptionSocket: IOException) {
                println("Socket don`t close")
            }
        }
    }

    override fun run() {
        try {
            Mock.byte.let {
                println("Send $Packet($it)")
                output.writeObject(it)
                output.flush()

                val response = input.readObject() as ByteArray
                val packet = Packet(response)
                println("Receive $packet")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        } finally {
            socket.close()
            output.close()
            count--
        }
    }
}
