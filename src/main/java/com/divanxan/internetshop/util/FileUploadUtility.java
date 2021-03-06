package com.divanxan.internetshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * This class is used to load images while administering the application
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
public class FileUploadUtility {
    private static final String ABS_PATH = "C:\\Repozitori\\GitHub\\InternetShop\\src\\main\\webapp\\assets\\images\\";
    private static String REAL_PATH = "";
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

    /**
     * This method is used to load images when administering the application
     *
     * @param request - HttpServletRequest
     * @param file -  MultipartFile for image
     * @param code - code for image information
     */
    public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
        REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
        logger.info(REAL_PATH);
        if (!new File(ABS_PATH).exists()) {
            new File(ABS_PATH).mkdirs();
        }
        if (!new File(REAL_PATH).exists()) {
            new File(REAL_PATH).mkdirs();
        }
        try {
            //server upload
            file.transferTo(new File(REAL_PATH + code + ".jpg"));
            //project directory upload
            file.transferTo(new File(ABS_PATH + code + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
