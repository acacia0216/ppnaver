package kr.co.pp.controller;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

public class HandlerController extends HttpServlet {

    private HashMap<String, Command> map;//키&벨류 형태로 파일을 읽어오기 위해 생성

    @Override
    public void init() throws ServletException {//WEB-INF의 commandList 받아서 키&벨류 형태로 저장하는 메소드
        System.out.println("init 1번");
        map = new HashMap<String, Command>();
        String path = getServletContext().getRealPath("WEB-INF/file/commandList.txt");
        File file = new File(path);
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                System.out.println(line);
                String[] temp = line.split("=");
                Object obj = Class.forName(temp[1]).newInstance();//벨류값으로 객체생성
                Command cmd = (Command)obj;//객체화 된 클래스가 저장되나?
                map.put(temp[0], cmd);//키&벨류 형태로 맵에 저장
            }
            System.out.println("try끝");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("init 끝");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost 2번");
        req.setCharacterEncoding("UTF-8");//읽어올때 한글깨짐 방지

        String url = req.getServletPath();//*.jsp까지만 읽어오기
        System.out.println("in"+url);
        Command cmd = map.get(url);//맵에서 url(*.jsp)을 읽어서 cmd에 넣기(cmd에는 객체가 저장되나?)

        String toUrl = cmd.process(req);//cmd(클래스(*.java))의 process메소드에 req 넣어서 호출

        RequestDispatcher rd = req.getRequestDispatcher(toUrl);
        rd.forward(req,resp);

    }
}
