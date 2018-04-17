package kr.co.pp.handler;

import kr.co.pp.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class DeleteHandler1 implements Command {

    @Override
    public String process(HttpServletRequest request) {
        String url = "./delete_page.jsp";
        return url;
    }
}
