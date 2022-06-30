package impl

import api.Descriptor
import model.Packet

class DescriptorImpl : Descriptor {

    override fun description(byteArray: ByteArray): Packet {
        return Packet(byteArray)
    }
}
