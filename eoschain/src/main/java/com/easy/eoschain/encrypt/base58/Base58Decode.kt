package com.easy.eoschain.encrypt.base58

import com.easy.eoschain.encrypt.BytesWithChecksum
import com.easy.eoschain.encrypt.hash.RIPEMD160Digest
import org.bitcoinj.core.Base58
import org.bitcoinj.core.Utils
import java.util.*

class Base58Decode {

    fun decode(base58Data: String): BytesWithChecksum {
        val data = Base58.decode(base58Data)
        val checksum = decodeChecksum(data)
        return BytesWithChecksum(Arrays.copyOfRange(data, 0, data.size - 4), checksum)
    }

    private fun decodeChecksum(data: ByteArray): Long {
        val hashData = ByteArray(data.size - 4)
        System.arraycopy(data, 0, hashData, 0, data.size - 4)

        val hashChecksum = Utils.readUint32(RIPEMD160Digest.hash(hashData), 0)
        val dataChecksum = Utils.readUint32(data, data.size - 4)

        if (hashChecksum != dataChecksum) {
            throw IllegalArgumentException("Invalid format, checksum mismatch")
        }

        return dataChecksum
    }
}