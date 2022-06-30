import impl.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class ServerTest {

    private lateinit var receiver: ReceiverImpl
    private lateinit var descriptor: DescriptorImpl
    private lateinit var processor: ProcessorImpl
    private lateinit var encryptor: EncryptorImpl
    private lateinit var sender: SenderImpl

    @BeforeTest
    fun setup() {
        receiver = ReceiverImpl()
        descriptor = DescriptorImpl()
        processor = ProcessorImpl()
        encryptor = EncryptorImpl()
        sender = SenderImpl()
    }

    @Test
    fun `Receiver Test`() {
        repeat(5) {
            val job = Thread {
                try {
                    receiver.receiveMessage()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.start()
            job.join()
        }
        Thread.sleep(100)
    }

    @Test
    fun `Descriptor Test`() {
        repeat(5) {
            val job = Thread {
                try {
                    receiver.receiveMessage()
                    descriptor.description()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.start()
            job.join()
        }
    }

    @Test
    fun `Processor Test`() {
        repeat(5) {
            val job = Thread {
                try {
                    receiver.receiveMessage()
                    descriptor.description()
                    processor.process()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.start()
            job.join()
        }
        Thread.sleep(100)
    }

    @Test
    fun `Encryptor Test`() {
        repeat(5) {
            val job = Thread {
                try {
                    receiver.receiveMessage()
                    descriptor.description()
                    processor.process()
                    encryptor.encrypt()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.start()
            job.join()
        }
        Thread.sleep(100)
    }

    @Test
    fun `Sender Test`() {
        repeat(5) {
            val job = Thread {
                try {
                    receiver.receiveMessage()
                    descriptor.description()
                    processor.process()
                    encryptor.encrypt()
                    sender.send()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.start()
            job.join()
        }
        Thread.sleep(100)
    }
}
