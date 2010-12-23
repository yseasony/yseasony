package org.yseasony.blog;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 10-12-23
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
public class ServletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = "!";
        if (StringUtils.isBlank(a))
        System.out.println("!!!!!5");
        else
            System.out.println("bbbbb12");
    }


}
