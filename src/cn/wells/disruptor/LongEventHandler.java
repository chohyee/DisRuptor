package cn.wells.disruptor;

import com.lmax.disruptor.EventHandler;
/**
 * �����ߣ������¼������ݣ���
 * @author clover
 *
 */
public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("Event: " + event.getValue()*event.getValue());
		System.out.println("Event: " + sequence);
		System.out.println("Event: " + endOfBatch);
	}

}
