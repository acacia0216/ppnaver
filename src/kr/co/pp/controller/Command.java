package kr.co.pp.controller;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    public String process(HttpServletRequest request);
}
