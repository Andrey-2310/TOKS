package transfering.packaging.stuffing


import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import transfering.packaging.PackagingAssistant
import java.lang._
import java.util

class ByteStuffingSc extends Stuffing {

  private val packagingAssistant = new PackagingAssistant

  override def encode(packet: util.List[Byte]): util.List[Byte] = {
    packagingAssistant.checkCorrectPacket("Encoding", packet)
    val data = packagingAssistant.unpack(packet)
    for (i <- 0 until data.size()) {
      if (data.get(i) == packagingAssistant.getStartByte) {
        data.remove(i)
        data.addAll(i, packagingAssistant.getAddToDataBytes)
      }
    }
    (packagingAssistant.pack(data) :+ packagingAssistant.getAddEndByte).asJava
  }

  override def decode(packet: util.List[Byte]): util.List[Byte] = {
    packagingAssistant.checkCorrectPacket("Decoding", packet)
    val data = packagingAssistant.unpack(packet.subList(0, packet.size() - 1))
    for (i <- 0 until data.size()) {
      if (data.subList(i, i + 1) == packagingAssistant.getAddToDataBytes) {
        data.remove(i + 1)
        data.set(i, packagingAssistant.getStartByte)
      }
    }
    packagingAssistant.pack(data)
  }
}
