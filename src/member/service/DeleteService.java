package member.service;

import java.util.Scanner;

import member.dao.MemberDAO;

public class DeleteService implements Member{
	Scanner scan = new Scanner(System.in);
	@Override
	public void execute() {
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pwd = scan.next();
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean state = memberDAO.delete(id , pwd);
		if(state)	System.out.println(memberDAO.getSu() + "개의 행 삭제완료");
		else System.out.println("비밀번호가 맞지 않습니다.");
	}

}
