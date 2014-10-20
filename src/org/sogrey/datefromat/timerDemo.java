package org.sogrey.datefromat;

/**
 * @author Administrator
 * 
 */
public class timerDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ���ڲ���
		Test t = new Test();
		// ��ʼ�߳�
		t.start();
		try {
			// 10000�����Ժ�����߳�
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// �����߳�
//		t.stop();

	}

	public static class Test implements Runnable {

		// ����һ���߳�
		Thread thread;
		// ����ֹͣ�̵߳ı�־
		private boolean flag = true;

		public Test() {
			thread = new Thread(this);
		}

		// ��Ϊ����ʵ����Runnable���ͱ�����run���������߳�����ʱ����������run����
		public void run() {
			// ��õ�ǰ��ʱ�䣬����Ϊ��λ
			long beginTime = System.currentTimeMillis();
			// �����ѹ�ȥ��ʱ��
			long time = 0;
			while (flag) {
				// ����дʵ�ּ�ʱ�Ĵ���
				// ���������ѹ�ȥ��ʱ��
				time = System.currentTimeMillis() - beginTime;
				System.out.println("�ѹ�" + time + "����");

				// ��ͣ�߳�1����,����ͣ�Ļ����԰��������ȥ��
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		// �����������̵߳ķ�����Ҳ���������߳�
		public void start() {
			thread.start();
		}

		// ��������ͣ�ķ�������ͣ�߳�
		public void Pause() {
			try {
				thread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// �����Ǽ����ķ���,�����߳�
		public void Resume() {
			thread.notifyAll();
		}

		// ֹͣ�߳�
		public void stop() {
			// ��flag���false������run�е�whileѭ���ͻ�ֹͣѭ��
			flag = false;
		}
	}
}
