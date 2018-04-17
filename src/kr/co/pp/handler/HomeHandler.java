package kr.co.pp.handler;

import kr.co.pp.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class HomeHandler implements Command {
    @Override
    public String process(HttpServletRequest request) {
        String url = "home.jsp";
        return url;
    }
}
