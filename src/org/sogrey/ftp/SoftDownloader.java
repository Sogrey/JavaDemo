package org.sogrey.ftp;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 文件下载器
 * FileDownloader loader = new FileDownloader(context, "http://browse.babasport.com/ejb3/ActivePort.exe",
 new File("D:\\androidsoft\\test"), 2);
 loader.getFileSize();//得到文件总大小
 try {
 loader.download(new DownloadProgressListener(){
 public void onDownloadSize(int size) {
 print("已经下载："+ size);
 }
 });
 } catch (Exception e) {
 e.printStackTrace();
 }
 */
public class SoftDownloader {
    private static String TAG = "FileDownloader";
//    private Context context;
    //	private FileService fileService;
	/* 已下载文件长度 */
    private int downloadSize = 0;
    /* 原始文件长度 */
    private int fileSize = 0;
    /* 线程数 */
    private SoftDownloadThread[] threads;
    /* 本地保存文件 */
    private File saveFile;
    /* 缓存各线程下载的长度*/
    private Map<Integer, Integer> data = new ConcurrentHashMap<Integer, Integer>();
    /* 每条线程下载的长度 */
    private int block;
    /* 下载路径  */
    private String downloadUrl;
    boolean notFinish = true;//下载未完成
//	private FileService fileService;
	private int downloadedPos;//已下载长度
    /**
     * 获取线程数
     */
    public int getThreadSize() {
        return threads.length;
    }
    /**
     * 获取文件大小
     * @return
     */
    public int getFileSize() {
        return fileSize;
    }
    /**
     * 累计已下载大小
     * @param size
     */
    protected synchronized void append(int size) {
        downloadSize += size;
    }
    /**
     * 更新指定线程最后下载的位置
     * @param threadId 线程id
     * @param pos 最后下载的位置
     */
    protected synchronized void update(int threadId, int pos) {
        this.data.put(threadId, pos);
//		this.fileService.update(this.downloadUrl, this.data);//数据入库
    }
    /**
     * 构建文件下载器
     * @param downloadUrl 下载路径
     * @param fileSaveDir 文件保存目录
     * @param threadNum 下载线程数
     * @param apkName 文件名
     * @param downloadedPos 已下载长度
     * @throws java.io.IOException
     */
    	public SoftDownloader( String downloadUrl, File fileSaveDir,
    			int threadNum,String apkName,int downloadedPos) throws IOException {
		try {
        this.downloadUrl = downloadUrl;
        this.downloadedPos = downloadedPos;
        URL url = new URL(this.downloadUrl);
        if(!fileSaveDir.exists()) fileSaveDir.mkdirs();
        this.threads = new SoftDownloadThread[threadNum];
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Referer", downloadUrl);
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.connect();
        printResponseHeader(conn);
        if (conn.getResponseCode()==200) {
            this.fileSize = conn.getContentLength();//根据响应获取文件大小
            if (this.fileSize <= 0) throw new RuntimeException("Unkown file size ");
            String filename = getFileName(conn);//获取文件名称
            this.saveFile = new File("D:/download/", apkName);//构建保存文件
            if(this.data.size()==this.threads.length){//下面计算所有线程已经下载的数据长度
                for (int i = 0; i < this.threads.length; i++) {//XXX 获取每条线程已下载长度，实现断点续传
                    this.downloadSize += this.data.get(i+1);
                }
                //Log.e(TAG, "已经下载的长度:" + this.downloadSize);
            }
            //计算每条线程下载的数据长度
            this.block = (this.fileSize % this.threads.length)==0? this.fileSize / this.threads.length : this.fileSize / this.threads.length + 1;
        }else{
            throw new RuntimeException("server no response ");
        }
		} catch (Exception e) {
			throw new RuntimeException("don't connection this url");
		}
    }
    /**
     * 获取文件名
     */
    private String getFileName(HttpURLConnection conn) {
        String filename = this.downloadUrl.substring(this.downloadUrl.lastIndexOf('/') + 1);
        if(filename==null || "".equals(filename.trim())){//如果获取不到文件名称
            for (int i = 0;; i++) {
                String mine = conn.getHeaderField(i);
                if (mine == null) break;
                if("content-disposition".equals(conn.getHeaderFieldKey(i).toLowerCase())){
                    Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
                    if(m.find()) return m.group(1);
                }
            }
            filename = UUID.randomUUID()+ ".tmp";//默认取一个文件名
        }
        return filename;
    }
    /**取消下载*/
    public void cancel(){
        //Log.e("SoftDownloader","取消下载");
        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i].cancel();
//        	if (! this.threads[i].interrupted()) {
//        		//Log.e("SoftDownloader","线程中断");
//        		this.threads[i].interrupt();
//			}
        }
    }
    
//    /**暂停下载*/
//    public void pause(Context context,int downloadedPos,int compeleteSize,String url){
//    	//Log.e("SoftDownloader","取消下载");
//    	for (int i = 0; i < this.threads.length; i++) {
//    		/*保存进度*/
//    		//TODO 
//    		BreakpointsOpera opera = BreakpointsOpera.getInstance(context);
//    		if (opera.hasBreakpoints(url)) {
//				opera.update( downloadedPos, url);
//			}else{
//				opera.saveBreakpoints( downloadedPos,compeleteSize, url);
//			}
//    		this.threads[i].cancel();
//    	}
//    }
    /**
     *  开始下载文件
     * @param listener 监听下载数量的变化,如果不需要了解实时下载的数量,可以设置为null
     * @return 已下载文件大小
     * @throws Exception
     */
    public int download(DownloadProgressListener listener) throws Exception {
        try {
            RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw");
            if(this.fileSize>0) randOut.setLength(this.fileSize);
            randOut.close();
            URL url = new URL(this.downloadUrl);
            if(this.data.size() != this.threads.length){
                this.data.clear();
                for (int i = 0; i < this.threads.length; i++) {
                    this.data.put(i+1, 0);//初始化每条线程已经下载的数据长度为0
                }
            }
            for (int i = 0; i < this.threads.length; i++) {//开启线程进行下载
                int downLength = this.data.get(i+1);
                if(downLength < this.block && this.downloadSize<this.fileSize){//判断线程是否已经完成下载,否则继续下载
                	//TODO  
                    this.threads[i] = new SoftDownloadThread(this, url, this.saveFile, this.block, this.data.get(i+1), i+1, downloadedPos);
                    this.threads[i].setPriority(7);
                    this.threads[i].start();
                }else{
                    this.threads[i] = null;
                }
            }
			//Log.e(TAG, "保存下载长度");
//			this.fileService.save(this.downloadUrl, this.data);//存库

            while (notFinish) {// 循环判断所有线程是否完成下载
                Thread.sleep(900);
                notFinish = false;//假定全部线程下载完成
                for (int i = 0; i < this.threads.length; i++){
                    if (this.threads[i] != null && !this.threads[i].isFinish()) {//如果发现线程未完成下载
                        notFinish = true;//设置标志为下载没有完成
                        if(this.threads[i].getDownLength() == -1){//如果下载失败,再重新下载
                        	//TODO 
                            this.threads[i] = new SoftDownloadThread(this, url, this.saveFile, this.block,downloadSize, i+1, downloadedPos);
                            this.threads[i].setPriority(7);
                            this.threads[i].start();
                        }
                    }
                }
                if(listener!=null) listener.onDownloadSize(this.downloadSize);//通知目前已经下载完成的数据长度
            }
//			fileService.delete(this.downloadUrl);//XXX 删除下载记录，最好是只做个标记而不删除
        } catch (Exception e) {
            //Log.e(TAG, e.toString());
//            DBWrapper wrapper = DBWrapper.getInstance(context);
//			Map<String, String> map = wrapper.selectSingleDataInfo(apkId);
//			String status = map.get(Constants.STATUS_DOWNLOAD);
//			if (!Constants.STATUS_DOWNLOADED.equals(status)) // 下载未完成
//            throw new Exception("file download fail");
        }
        return this.downloadSize;
    }
    /**
     * 获取Http响应头字段
     * @param http
     * @return
     */
    public static Map<String, String> getHttpResponseHeader(HttpURLConnection http) {
        Map<String, String> header = new LinkedHashMap<String, String>();
        for (int i = 0;; i++) {
            String mine = http.getHeaderField(i);
            if (mine == null) break;
            header.put(http.getHeaderFieldKey(i), mine);
        }
        return header;
    }
    /**
     * 打印Http头字段
     * @param http
     */
    public static void printResponseHeader(HttpURLConnection http){
        Map<String, String> header = getHttpResponseHeader(http);
        for(Map.Entry<String, String> entry : header.entrySet()){
            String key = entry.getKey()!=null ? entry.getKey()+ ":" : "";
            print(key+ entry.getValue());
        }
    }
    private static void print(String msg){
        //Log.i(TAG, msg);
    }

}
