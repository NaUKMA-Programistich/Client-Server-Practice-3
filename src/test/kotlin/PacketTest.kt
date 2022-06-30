
import model.Packet
import org.junit.jupiter.api.assertThrows
import utils.Mock
import kotlin.test.Test
import kotlin.test.assertEquals

class PacketTest {

    @Test
    fun `Bytes to Packet`() {
        val packet = Packet(Mock.byte)
        println(packet)
    }

    @Test
    fun `Bytes to Packet and check magic byte`() {
        val bytes = Mock.packet.convertToBytes()
        assertEquals(bytes[0], 0x13)
    }

    @Test
    fun `Equals packet from bytes`() {
        val packet = Packet(Mock.byte)
        assertEquals(Mock.packet.convertToBytes().size, packet.convertToBytes().size)
        val size = Mock.packet.convertToBytes().size
        repeat(size) {
            assertEquals(Mock.packet.convertToBytes()[it], packet.convertToBytes()[it])
        }
    }

    @Test
    fun `Change magic byte`() {
        val bytes = Mock.packet.convertToBytes()
        bytes[0] = 0x15
        assertThrows<RuntimeException> {
            val packet = Packet(bytes)
            println(packet)
        }
    }

    @Test
    fun `Change CRC`() {
        val bytes = Mock.packet.convertToBytes()
        bytes[3] = 0x15
        assertThrows<RuntimeException> {
            val packet = Packet(bytes)
            println(packet)
        }
    }
}
