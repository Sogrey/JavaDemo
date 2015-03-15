package org.sogrey.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpApche {
	private static FTPClient ftpClient = new FTPClient();
	private static String encoding = System.getProperty("file.encoding");

	/**
	 * �������ļ��ϴ���FTP��������
	 * 
	 * @param url
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param path
	 *            FTP����������Ŀ¼,����Ǹ�Ŀ¼��Ϊ��/��
	 * @param filename
	 *            �ϴ���FTP�������ϵ��ļ���
	 * @param localPath
	 *            ����Ҫ�ϴ��ļ�����·�������ļ�#**#�ָ
	 */
	public static void testUpLoadFromDisk(final String url, final int port,
			final String username, final String password, final String path,
			final String filename, final String localPath) {
//		String[] localPathArr = localPath.split("#\\*\\*#");
//		for (int i = 0; i < localPathArr.length; i++) {
//			final String localPathSingle = localPathArr[i];
//			final String filename_ = filename[i];
			final String filename_ = filename;
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						// FileInputStream in = new FileInputStream(new
						// File("E:/����.txt"));
						FileInputStream in = new FileInputStream(new File(
//								localPathSingle));
								localPath));
						
						// boolean flag = uploadFile("127.0.0.1", 21,
						// "zlb","123", "/", "����.txt", in);
						boolean flag = uploadFile(url, port, username,
								password, path, filename_, in);
						System.out.println(flag);
						// TODO �����ϴ���ʶ
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}).start();
//		}
	}

	public static void testUpLoadFromDisk11(final String url, final int port,
			final String username, final String password, final String path,
			final String[] filename, final String localPath) throws SocketException {
		String[] localPathArr = localPath.split("#\\*\\*#");
		 // �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
		 // ftpClient.connect(url);
		 try {
			ftpClient.connect(url, port);
			// ����FTP������
			// ��¼
			ftpClient.login(username, password);
			ftpClient.setControlEncoding(encoding);
			for (int i = 10; i < localPathArr.length+10; i++) {
				final String localPathSingle = localPathArr[i-10];
				final String filename_ = filename[i-10];
				new Thread(new Runnable() {
					
					private boolean result;
					
					@Override
					public void run() {
						try {
							// FileInputStream in = new FileInputStream(new
							// File("E:/����.txt"));
							FileInputStream input = new FileInputStream(new File(
									localPathSingle));//XXX  
							////////////////////////////////////////////
							try {
								int reply;
								
								// �����Ƿ����ӳɹ�
								reply = ftpClient.getReplyCode();
								if (!FTPReply.isPositiveCompletion(reply)) {
									System.out.println("����ʧ��");
									ftpClient.disconnect();
									Thread.currentThread().stop();
//								return result;
									return;
								}
								
								// ת�ƹ���Ŀ¼��ָ��Ŀ¼��
								boolean change = ftpClient.changeWorkingDirectory(path);
								ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
								if (change) {
									result = ftpClient.storeFile(
											new String(filename_.getBytes(encoding), "iso-8859-1"),
											input);
									if (result) {
										System.out.println("�ϴ��ɹ�!");
										Thread.currentThread().stop();
									}
								}
								input.close();
								
							} catch (IOException e) {
								e.printStackTrace();
							} 
							// boolean flag = uploadFile("127.0.0.1", 21,
							// "zlb","123", "/", "����.txt", in);
//						boolean flag = uploadFile(url, port, username,
//								password, path, filename_, input);
							
							
							////////////////////////////////////////////
							
							System.out.println(result);
							// TODO �����ϴ���ʶ
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
//			ftpClient.logout();//XXX
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		 finally {
//			if (ftpClient.isConnected()) {
//				try {
//					ftpClient.disconnect();
//				} catch (IOException ioe) {
//				}
//			}
//		}
	}

	/**
	 * ��FTP���������ļ����ص�����
	 * 
	 * @param url
	 *            FTP������hostname 122.114.22.74
	 * @param port
	 *            FTP�������˿� 21
	 * @param username
	 *            FTP��¼�˺� client
	 * @param password
	 *            FTP��¼���� lirenkj20141111
	 * @param filePath
	 *            Ҫ���ص��ļ���(FTP���ļ�����·����ȥ����<br>
	 *            ����
	 *            http://media.liren-eschoolbag.com/upload/client/homework/826
	 *            /TEA/Tea_20150227052901.zip <br>
	 *            filePath =
	 *            /upload/client/homework/826/TEA/Tea_20150227052901.zip)
	 * @param localPath
	 *            ���غ󱣴浽���ص�·��<br>
	 *            ����D:/download/
	 */
	public void testDownFile(final String url, final int port,
			final String username, final String password,
			final String filePath, final String localPath) {

		// "filePath":"/ftpfile/20150211174505.mov",
		Map<String, String> map = getFileName(filePath);// ��ȡ�ļ���,�м���
		final String remotePath = map.get("middleName");
		final String fileName = map.get("fileName");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// boolean flag = downFile("127.0.0.1", 21, "zlb",
					// "123", "/", "����.txt", "D:/");
					boolean flag = downFile(url, port, username, password,
							remotePath, fileName, localPath);
					System.out.println(flag);
					// TODO ���ر�־�������ؽ��
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * �ָ��ļ�·����ȡ�ļ���
	 * 
	 * @param filePath
	 * @return Map��<br>
	 *         (key) fileName:�ļ���<br>
	 *         (key) middleName:���ļ���ʣ�ಿ��
	 */
	private Map<String, String> getFileName(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = "";
		String middleName = "";
		// if(!TextUtils.isEmpty(filePath)){
		fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		middleName = filePath.replace(fileName, "");
		// }

		map.put("fileName", fileName);// 20150211174505.mov
		map.put("middleName", middleName);// /ftpfile/
		return map;
	}

	/**
	 * Description: ��FTP�������ϴ��ļ�
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param path
	 *            FTP����������Ŀ¼,����Ǹ�Ŀ¼��Ϊ��/��
	 * @param filename
	 *            �ϴ���FTP�������ϵ��ļ���
	 * @param input
	 *            �����ļ�������
	 * @return �ɹ�����true�����򷵻�false
	 */
	private static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean result = false;

		try {
			int reply;
			 // �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			 // ftpClient.connect(url);
			 ftpClient.connect(url, port);// ����FTP������
			 // ��¼
			 ftpClient.login(username, password);
			ftpClient.setControlEncoding(encoding);
			// �����Ƿ����ӳɹ�
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("����ʧ��");
				ftpClient.disconnect();
				return result;
			}

			// ת�ƹ���Ŀ¼��ָ��Ŀ¼��
			boolean change = ftpClient.changeWorkingDirectory(path);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (change) {
				result = ftpClient.storeFile(
						new String(filename.getBytes(encoding), "iso-8859-1"),
						input);
				if (result) {
					System.out.println("�ϴ��ɹ�!");
				}
			}
			input.close();
			 ftpClient.logout();//XXX
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: ��FTP�����������ļ�
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP������hostname 122.114.22.74
	 * @param port
	 *            FTP�������˿� 21
	 * @param username
	 *            FTP��¼�˺� client
	 * @param password
	 *            FTP��¼���� lirenkj20141111
	 * @param remotePath
	 *            FTP�������ϵ����·��
	 *            http://media.liren-eschoolbag.com/upload/client/homework
	 *            /826/TEA/Tea_20150227052901.zip
	 * @param fileName
	 *            Ҫ���ص��ļ���
	 * @param localPath
	 *            ���غ󱣴浽���ص�·��
	 * @return
	 */
	private static boolean downFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean result = false;
		File file = new File(localPath); // ������±����Ŀ¼
		if (!file.exists()) {
			try {
				// ����ָ����·�������ļ���
				file.mkdirs();
			} catch (Exception e) {
			}
		}
		try {
			int reply;
			ftpClient.setControlEncoding(encoding);

			/*
			 * Ϊ���ϴ������������ļ�����Щ�ط�����ʹ������������� new
			 * String(remotePath.getBytes(encoding),"iso-8859-1")ת�롣 �������ԣ�ͨ������
			 */
			// FTPClientConfig conf = new
			// FTPClientConfig(FTPClientConfig.SYST_NT);
			// conf.setServerLanguageCode("zh");

			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftpClient.connect(url, port);
			ftpClient.login(username, password);// ��¼
			// �����ļ���������Ϊ������
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// ��ȡftp��¼Ӧ�����
			reply = ftpClient.getReplyCode();
			// ��֤�Ƿ��½�ɹ�
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				return result;
			}
			// ת�Ƶ�FTP������Ŀ¼��ָ����Ŀ¼��
			ftpClient.changeWorkingDirectory(new String(remotePath
					.getBytes(encoding), "iso-8859-1"));
			// ��ȡ�ļ��б�
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					System.out.println(localFile.toString());
					OutputStream is = new FileOutputStream(localFile);
					ftpClient.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * �ǵ�½ֱ������
	 * 
	 * @param path
	 *            ftp�ļ�·��
	 * @param savedir
	 *            ����·��
	 * @param fileName
	 *            �����ļ���
	 */
	private void download(final String path, final File savedir,
			final String fileName) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(path + "\n" + savedir + "\n" + fileName);
					SoftDownloader loader = new SoftDownloader(path, savedir,
							1, fileName, 0);
					int totleSize = loader.getFileSize();// �õ��ļ��ܴ�С
					try {
						int downloadSize = loader
								.download(new DownloadProgressListener() {
									public void onDownloadSize(int size) {
										System.out.println("�Ѿ����أ�" + size);
									}
								});
						if (downloadSize == totleSize) {
							System.out.println("true:download finished");
							Thread.currentThread().stop();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public final static String FTP_URL_UPLOAD= "125.76.237.48";//�ϴ�FTP����
	public final static String FTP_URL_UPLOAD_MID= "/gtcs/";//�ϴ�FTP·��
	public final static int FTP_URL_PORT= 21;//�˿�
	public final static String FTP_URL_USERNAME= "gtcs";//�û���
	public final static String FTP_URL_PWD= "webtec+88323238";//����
	
	public static void main(String[] args) {
		FtpApche fa = new FtpApche();

		/* �����������ǵ�¼ֱ�����ء��������� */
		// File path = new File("D:/download/");
		// fa.download(
		// "http://ftp-idc.pconline.com.cn/e187416fbb733f29f0632e9b2d261d32/pub/download/201010/FlashFXP50_3804_Setup.exe",
		// path, "FlashFXP50_3804_Setup.exe");
		// fa.download(
		// "http://125.76.237.48:8120/ftpfile/20150309101807.png",
		// path, "FlashFXP50_3804_Setup.exe");

		/* �����������ǵ�¼ֱ�����ء��������� */

		/* ������������¼ֱ�����ء��������� */
		// downFile("221.229.166.197",
		// 21,
		// "sogrey",
		// "0423889",
		// "ftp://221.229.166.197/web/tmp/VID_20150227_175021.3gp",
		// "VID_20150227_175021.3gp",
		// "D:/download/");
		/* ������������¼ֱ�����ء��������� */

		/* ������������¼ֱ���ϴ����������� */
		// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
		// ftpClient.connect(url);
//		try {
//			ftpClient.connect("221.229.166.197", 21);
			// ����FTP������
			// ��¼
//			ftpClient.login("sogrey", "0423889");
//			for (int i = 0; i < 1; i++) {
//				testUpLoadFromDisk("221.229.166.197", 21, "sogrey", "0423889",
//						"/sogrey/web/tmp/upload/", "test.png",
//						"C:\\Users\\Administrator\\Desktop\\test.png");
				FileInputStream input = null;
				try {
					input = new FileInputStream(new File(
							"C:\\Users\\Administrator\\Desktop\\test.png"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean tag = uploadFile("221.229.166.197", 21, "sogrey", "0423889", 
						"/sogrey/web/tmp/upload/", "test.png", input);
				System.out.println(tag);
//				testUpLoadFromDisk(FTP_URL_UPLOAD, 
//						FTP_URL_PORT, 
//FTP_URL_USERNAME, FTP_URL_PWD,
//						FTP_URL_UPLOAD_MID, "test.png",
//						"C:\\Users\\Administrator\\Desktop\\test.png");
//			}
//		String[] fileNames = {"001.mov","002.mov","003.mov","004.mov","005.mov"};
//		try {
//			testUpLoadFromDisk11("221.229.166.197", 21, "sogrey", "0423889",
//							"/sogrey/web/tmp/upload/", fileNames,
//							"C:\\Users\\Administrator\\Desktop\\20150213122119.mov"
//							+ "#**#"
//							+ "C:\\Users\\Administrator\\Desktop\\20150213122119.mov"
//							+ "#**#"
//							+ "C:\\Users\\Administrator\\Desktop\\20150213122119.mov"
//							+ "#**#"
//							+ "C:\\Users\\Administrator\\Desktop\\20150213122119.mov"
//							+ "#**#"
//							+ "C:\\Users\\Administrator\\Desktop\\20150213122119.mov"
//									);
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			ftpClient.logout();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		/* ������������¼ֱ���ϴ����������� */
	}
}