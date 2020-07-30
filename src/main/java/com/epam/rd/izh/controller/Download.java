package com.epam.rd.izh.controller;

import com.epam.rd.izh.repository.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class Download extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    FileRepository fileRepository = new FileRepository();
    public static final int BUFFER_SIZE = 1024 * 1024 * 20;

    /**
     * Загрузка с сервера
     */
    @GetMapping("/book/download/{author}/{title}")
    public void download(HttpServletResponse httpServletResponse, @PathVariable String author, @PathVariable String title){

        String downloadTitle = title + ".fb2.zip";
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/zip");
        try {
            httpServletResponse.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(title, "UTF-8") + ".fb2.zip\"");
        } catch (UnsupportedEncodingException e) {
            LOGGER.fatal(e.getMessage());
        }
        File downloadFile = fileRepository.getBookByAuthorAndTitle(title, author);
        System.out.println(downloadTitle + " " + title + " " + author + " " + downloadFile.getName());

        try {
            OutputStream outputStream = httpServletResponse.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(downloadFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;

            while ((length = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            fileInputStream.close();
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}
