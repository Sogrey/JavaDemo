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
	 * 将本地文件上传到FTP服务器上
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param localPath
	 *            本地要上传文件绝对路径（多文件#**#分割）
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
						// File("E:/号码.txt"));
						FileInputStream in = new FileInputStream(new File(
//								localPathSingle));
								localPath));
						
						// boolean flag = uploadFile("127.0.0.1", 21,
						// "zlb","123", "/", "哈哈.txt", in);
						boolean flag = uploadFile(url, port, username,
								password, path, filename_, in);
						System.out.println(flag);
						// TODO 返回上传标识
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
		 // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
		 // ftpClient.connect(url);
		 try {
			ftpClient.connect(url, port);
			// 连接FTP服务器
			// 登录
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
							// File("E:/号码.txt"));
							FileInputStream input = new FileInputStream(new File(
									localPathSingle));//XXX  
							////////////////////////////////////////////
							try {
								int reply;
								
								// 检验是否连接成功
								reply = ftpClient.getReplyCode();
								if (!FTPReply.isPositiveCompletion(reply)) {
									System.out.println("连接失败");
									ftpClient.disconnect();
									Thread.currentThread().stop();
//								return result;
									return;
								}
								
								// 转移工作目录至指定目录下
								boolean change = ftpClient.changeWorkingDirectory(path);
								ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
								if (change) {
									result = ftpClient.storeFile(
											new String(filename_.getBytes(encoding), "iso-8859-1"),
											input);
									if (result) {
										System.out.println("上传成功!");
										Thread.currentThread().stop();
									}
								}
								input.close();
								
							} catch (IOException e) {
								e.printStackTrace();
							} 
							// boolean flag = uploadFile("127.0.0.1", 21,
							// "zlb","123", "/", "哈哈.txt", in);
//						boolean flag = uploadFile(url, port, username,
//								password, path, filename_, input);
							
							
							////////////////////////////////////////////
							
							System.out.println(result);
							// TODO 返回上传标识
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
	 * 将FTP服务器上文件下载到本地
	 * 
	 * @param url
	 *            FTP服务器hostname 122.114.22.74
	 * @param port
	 *            FTP服务器端口 21
	 * @param username
	 *            FTP登录账号 client
	 * @param password
	 *            FTP登录密码 lirenkj20141111
	 * @param filePath
	 *            要下载的文件名(FTP上文件绝对路径除去域名<br>
	 *            例：
	 *            http://media.liren-eschoolbag.com/upload/client/homework/826
	 *            /TEA/Tea_20150227052901.zip <br>
	 *            filePath =
	 *            /upload/client/homework/826/TEA/Tea_20150227052901.zip)
	 * @param localPath
	 *            下载后保存到本地的路径<br>
	 *            例：D:/download/
	 */
	public void testDownFile(final String url, final int port,
			final String username, final String password,
			final String filePath, final String localPath) {

		// "filePath":"/ftpfile/20150211174505.mov",
		Map<String, String> map = getFileName(filePath);// 获取文件名,中间名
		final String remotePath = map.get("middleName");
		final String fileName = map.get("fileName");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// boolean flag = downFile("127.0.0.1", 21, "zlb",
					// "123", "/", "哈哈.txt", "D:/");
					boolean flag = downFile(url, port, username, password,
							remotePath, fileName, localPath);
					System.out.println(flag);
					// TODO 返回标志标明下载结果
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 分割文件路径获取文件名
	 * 
	 * @param filePath
	 * @return Map：<br>
	 *         (key) fileName:文件名<br>
	 *         (key) middleName:除文件名剩余部分
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
	 * Description: 向FTP服务器上传文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            本地文件输入流
	 * @return 成功返回true，否则返回false
	 */
	private static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean result = false;

		try {
			int reply;
			 // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			 // ftpClient.connect(url);
			 ftpClient.connect(url, port);// 连接FTP服务器
			 // 登录
			 ftpClient.login(username, password);
			ftpClient.setControlEncoding(encoding);
			// 检验是否连接成功
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接失败");
				ftpClient.disconnect();
				return result;
			}

			// 转移工作目录至指定目录下
			boolean change = ftpClient.changeWorkingDirectory(path);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (change) {
				result = ftpClient.storeFile(
						new String(filename.getBytes(encoding), "iso-8859-1"),
						input);
				if (result) {
					System.out.println("上传成功!");
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
	 * Description: 从FTP服务器下载文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname 122.114.22.74
	 * @param port
	 *            FTP服务器端口 21
	 * @param username
	 *            FTP登录账号 client
	 * @param password
	 *            FTP登录密码 lirenkj20141111
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 *            http://media.liren-eschoolbag.com/upload/client/homework
	 *            /826/TEA/Tea_20150227052901.zip
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	private static boolean downFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean result = false;
		File file = new File(localPath); // 软件更新保存根目录
		if (!file.exists()) {
			try {
				// 按照指定的路径创建文件夹
				file.mkdirs();
			} catch (Exception e) {
			}
		}
		try {
			int reply;
			ftpClient.setControlEncoding(encoding);

			/*
			 * 为了上传和下载中文文件，有些地方建议使用以下两句代替 new
			 * String(remotePath.getBytes(encoding),"iso-8859-1")转码。 经过测试，通不过。
			 */
			// FTPClientConfig conf = new
			// FTPClientConfig(FTPClientConfig.SYST_NT);
			// conf.setServerLanguageCode("zh");

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(url, port);
			ftpClient.login(username, password);// 登录
			// 设置文件传输类型为二进制
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 获取ftp登录应答代码
			reply = ftpClient.getReplyCode();
			// 验证是否登陆成功
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				return result;
			}
			// 转移到FTP服务器目录至指定的目录下
			ftpClient.changeWorkingDirectory(new String(remotePath
					.getBytes(encoding), "iso-8859-1"));
			// 获取文件列表
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
	 * 非登陆直接下载
	 * 
	 * @param path
	 *            ftp文件路径
	 * @param savedir
	 *            保存路径
	 * @param fileName
	 *            保存文件名
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
					int totleSize = loader.getFileSize();// 得到文件总大小
					try {
						int downloadSize = loader
								.download(new DownloadProgressListener() {
									public void onDownloadSize(int size) {
										System.out.println("已经下载：" + size);
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

	public final static String FTP_URL_UPLOAD= "125.76.237.48";//上传FTP域名
	public final static String FTP_URL_UPLOAD_MID= "/gtcs/";//上传FTP路径
	public final static int FTP_URL_PORT= 21;//端口
	public final static String FTP_URL_USERNAME= "gtcs";//用户名
	public final static String FTP_URL_PWD= "webtec+88323238";//密码
	
	public static void main(String[] args) {
		FtpApche fa = new FtpApche();

		/* ↓↓↓↓↓非登录直接下载↓↓↓↓↓ */
		// File path = new File("D:/download/");
		// fa.download(
		// "http://ftp-idc.pconline.com.cn/e187416fbb733f29f0632e9b2d261d32/pub/download/201010/FlashFXP50_3804_Setup.exe",
		// path, "FlashFXP50_3804_Setup.exe");
		// fa.download(
		// "http://125.76.237.48:8120/ftpfile/20150309101807.png",
		// path, "FlashFXP50_3804_Setup.exe");

		/* ↑↑↑↑↑非登录直接下载↑↑↑↑↑ */

		/* ↓↓↓↓↓登录直接下载↓↓↓↓↓ */
		// downFile("221.229.166.197",
		// 21,
		// "sogrey",
		// "0423889",
		// "ftp://221.229.166.197/web/tmp/VID_20150227_175021.3gp",
		// "VID_20150227_175021.3gp",
		// "D:/download/");
		/* ↑↑↑↑↑登录直接下载↑↑↑↑↑ */

		/* ↓↓↓↓↓登录直接上传↓↓↓↓↓ */
		// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
		// ftpClient.connect(url);
//		try {
//			ftpClient.connect("221.229.166.197", 21);
			// 连接FTP服务器
			// 登录
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
		/* ↑↑↑↑↑登录直接上传↑↑↑↑↑ */
	}
}