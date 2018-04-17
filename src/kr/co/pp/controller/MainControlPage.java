package kr.co.pp.controller;

import kr.co.pp.dao.MemberDAO;
import kr.co.pp.vo.MemberVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainControlPage extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        String url = "./home.jsp";
        String cmd = req.getParameter("cmd");
        List<MemberVO> list = null;
        cmd = cmd == null ? "" : cmd;
        System.out.println("메인컨트롤페이지의 cmd : " + cmd);

        if (cmd.equals("regist")) {//회원정보 저장
            list = new ArrayList<MemberVO>();
            MemberVO vo = new MemberVO();
            boolean flag = false;
            System.out.println("레지스트 들어옴");
            if (vo != null) {
                System.out.println("객체화 시작");
                vo.setId(req.getParameter("id"));
                vo.setPw(req.getParameter("pw"));
                vo.setName(req.getParameter("name"));
                vo.setGender(req.getParameter("gender"));
                String by = req.getParameter("birthyear");
                String bm = req.getParameter("birthmonth");
                String bd = req.getParameter("birthday");
                String val[] = {by, bm, bd};
                vo.setBirth(val);
                vo.setEmail(req.getParameter("email"));
                vo.setHp(req.getParameter("hp"));
                System.out.println("객체 생성 완료");

                System.out.println("한글 깨짐 확인 : " + vo.getName());

                list.add(vo);
                System.out.println("리스트 추가 완료");
                MemberDAO dao = new MemberDAO();
                flag = dao.insert(vo);

                if (flag == true) {
                    System.out.println(vo.getId() + "님이 등록되었습니다.");
                } else if (flag == false) {
                    System.out.println("회원정보가 넘어오지 못함");
                    url = "./error.jsp";
                }
                url = "./home.jsp";
            }
        }else if (cmd.equals("edit_page")) {//회원정보 수정 페이지 연결
            System.out.println("회원정보 수정페이지 연결완료");
            url = "./edit_page.jsp";
        }else if(cmd.equals("edit_member_page")){
            String id = req.getParameter("id");
            //회원정보 가져오기
            MemberDAO dao = new MemberDAO();
            MemberVO vo = null;
            vo = dao.searchMember(id);
            req.setAttribute("vo",vo);
            System.out.println("회원정보 가져오기 완료");
            url = "./member_edit.jsp";
        }else if(cmd.equals("edit_member")){//회원정보 수정
            MemberDAO dao = new MemberDAO();
            MemberVO vo = new MemberVO();
            vo.setId(req.getParameter("id"));
            vo.setPw(req.getParameter("pw"));
            vo.setName(req.getParameter("name"));
            vo.setGender(req.getParameter("gender"));
            vo.setBirth(req.getParameterValues("birth"));
            vo.setEmail(req.getParameter("email"));
            vo.setHp(req.getParameter("hp"));
            //수정한 회원정보 가져오기
            dao.update(vo);
            System.out.println("DAO에 들어갔다 왔음");
            url = "./home.jsp";
        }else if(cmd.equals("delete_member_page")){//회원정보 삭제 페이지 연결
            url = "./delete_page.jsp";
        }else if(cmd.equals("delete")){//회원정보 삭제
            String id = req.getParameter("id");
            MemberDAO dao = new MemberDAO();
            dao.delete(id);
            System.out.println("아이디 삭제되었음");
            url = "./home.jsp";
        }else if(cmd.equals("join")){//회원 등록화면
            url = "./Naver_regist.jsp";
        }

        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(req, resp);
    }
}

