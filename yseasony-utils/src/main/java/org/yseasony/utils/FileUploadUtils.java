package org.yseasony.utils;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtils {

    private static Logger logger = LoggerFactory
            .getLogger(FileUploadUtils.class);
    private static int sizeThreshold = 4096;
    private static String tempPath = "java.io.tmpdir";

    @SuppressWarnings("unchecked")
    public static List<FileItem> read(HttpServletRequest request) {
        File tempfile = new File(System.getProperty(tempPath));
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(sizeThreshold); // 设置缓冲区大小，这里是4kb
        diskFileItemFactory.setRepository(tempfile); // 设置缓冲区目录
        ServletFileUpload fu = new ServletFileUpload(diskFileItemFactory);
        fu.setSizeMax(4194304);
        try {
            return fu.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            logger.error("", e);
            return Collections.EMPTY_LIST;
        }
    }
}
