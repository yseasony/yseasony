package org.yseasony.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIUtils {

    private HSSFSheet sheet;

    public POIUtils(InputStream inputStream) {
        try {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
            sheet = wb.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

}
