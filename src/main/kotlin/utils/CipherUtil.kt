package utils

import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CipherUtil {

    private const val secretBytes = "AlexAlexAlexAlex"
    private const val sizeBytes = 16
    private val key = SecretKeySpec(secretBytes.toByteArray(StandardCharsets.UTF_8), "AES")
    private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

    private fun init(mode: Int) {
        cipher.init(mode, key, IvParameterSpec(ByteArray(sizeBytes)))
    }

    fun ByteArray.decodeCipher(): ByteArray {
        init(Cipher.DECRYPT_MODE)
        return cipher.doFinal(this)
    }

    fun ByteArray.encodeCipher(): ByteArray {
        init(Cipher.ENCRYPT_MODE)
        return cipher.doFinal(this)
    }
}
