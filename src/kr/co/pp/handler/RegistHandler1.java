package kr.co.pp.handler;

import kr.co.pp.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistHandler1 implements Command {
    @Override
    public String process(HttpServletRequest request) {
        String url = "./Naver_regist.jsp";
        return url;
    }
}
