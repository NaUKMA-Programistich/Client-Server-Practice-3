package network.tcp

import java.io.IOException
import java.net.ServerSocket

const val SERVER_PORT = 8080

fun main() {
    val serverSocket = ServerSocket(SERVER_PORT)
    println("Start server TCP")

    try {
        while (true) {
            val existSocket = serverSocket.accept()
            try {
                val storeServerTCP = StoreServerTCP(existSocket)
                storeServerTCP.start()
            } catch (exception: IOException) {
                println("IOException socket server TCP")
                existSocket.close()
            } catch (exception: ClassNotFoundException) {
                println("ClassNotFoundException socket server TCP")
                existSocket.close()
            }
            Thread.sleep(100)
        }
    } finally {
        serverSocket.close()
        println("Close socket server TCP")
    }
}
