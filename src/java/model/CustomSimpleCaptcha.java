package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import static nl.captcha.Captcha.NAME;
import nl.captcha.backgrounds.SquigglesBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.SimpleCaptchaServlet;

public class CustomSimpleCaptcha extends SimpleCaptchaServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Captcha myCaptcha = new Captcha.Builder(300, 50)
                .addText()
                .addBackground(new SquigglesBackgroundProducer())
                .addNoise()
                .addBorder()
                .build();
        
        CaptchaServletUtil.writeImage(response, myCaptcha.getImage());
        request.getSession().setAttribute(NAME, myCaptcha);
    }
}
