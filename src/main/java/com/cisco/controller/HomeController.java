package com.cisco.controller;

import com.cisco.excutor.Excutor;
//import com.webex.webapp.common.util.security.AppTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

class HttpResponse<Type> {
    private int code;
    private String message;
    private Type data;

    public static<T> HttpResponse<T> ok(T data) {
        return new HttpResponse<T>(200, "succ", data);
    }

    public static<T> HttpResponse<T> fail(T data) {
        return new HttpResponse<T>(500, "fail", data);
    }

    public HttpResponse(int status, String message, Type data) {
        this.code = status;
        this.message = message;
        this.data = data;
    }

    public HttpResponse setData(Type data) {
        this.data = data;
        return this;
    }

    public Type getData() {
        return this.data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

@RestController
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/apptoken", method = RequestMethod.GET)
    public HttpResponse getApptoken(@RequestParam Map<String, String> params) {
        String appName = null;
        Object appNameObj = params.get("appName");
        if (StringUtils.isEmpty(appNameObj)) {
            appName = "APP_ADDIN";
        } else {
            appName = appNameObj.toString();
        }
        try {
            return HttpResponse.ok(null);//AppTokenUtil.makeTicket2(appName));
        } catch (Exception e) {
            LOG.error("get apptoke failed", e);
            System.out.println(e.toString());
            return HttpResponse.fail(null);
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/site", method = RequestMethod.GET)
    public HttpResponse getSiteId(@RequestParam Map<String, String> params) {
        String site = params.get("site");
        try {
            String appToken = "iii";//AppTokenUtil.makeTicket2("APP_ADDIN");
            Map<String, Object> pyparam = new HashMap<>();
            pyparam.put("cmd", "GET_SITE");
            pyparam.put("site", site);
            pyparam.put("token", appToken);
            Excutor.execute(pyparam);
        } catch (Exception e) {
            LOG.error("get apptoke failed", e);
            System.out.println(e.toString());
            return HttpResponse.fail(null);
        }
        return HttpResponse.fail(null);
    }
}
