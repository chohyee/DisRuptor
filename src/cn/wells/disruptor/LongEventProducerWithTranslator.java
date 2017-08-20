package cn.wells.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
/**
 * ע�⣬���� ringBuffer.publish ������������� finally ����ȷ������õ����ã�
 * ���ĳ������� sequence δ���ύ��������������ķ����������������� producer��
 * Disruptor ���ṩ����һ����ʽ�ĵ����������ϲ�������ȷ�� publish ���ǵõ����á�
 * ===============================================================
 * With version 3.0 of the Disruptor a richer Lambda-style API was 
 * added to help developers by encapsulating this complexity within
 * the Ring Buffer, so post-3.0 the preferred approach for publishing
 * messages is via the Event Publisher/Event Translator portion of the API. E.g.
 * @author clover
 *
 */
public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;
	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	} 
	private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR =
			new EventTranslatorOneArg<LongEvent,ByteBuffer>()
			{
				 public void translateTo(LongEvent event, long sequence, ByteBuffer bb)
		         {
		             event.set(bb.getLong(0));
		         }
			};
	public void onData(ByteBuffer bb)
    {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
