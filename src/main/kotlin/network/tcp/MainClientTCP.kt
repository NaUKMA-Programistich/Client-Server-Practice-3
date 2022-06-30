package network.tcp

import java.net.InetAddress

fun main() {
    while (true) {
        Thread.sleep(1000)
        if (StoreClientTCP.count < StoreClientTCP.max) {
            StoreClientTCP(InetAddress.getByName(null))
        }
    }
}
