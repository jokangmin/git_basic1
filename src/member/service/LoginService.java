package member.service;

import java.util.Scanner;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements Member{
	Scanner scan = new Scanner(System.in);
	@Override
	public void execute() {
		
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pwd = scan.next();
		MemberDAO memberDAO = MemberDAO.getInstance();
		//MemberDTO memberDTO = new MemberDTO();
		String name = memberDAO.login(id , pwd);
		if(name == null)	System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		else	System.out.println(name + "님 로그인 성공");
	}

}
