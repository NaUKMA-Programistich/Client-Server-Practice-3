package api

import java.io.ObjectOutputStream
import java.net.InetAddress

interface Sender {
    fun send(output: ObjectOutputStream)
    fun send(address: InetAddress, port: Int)
}
