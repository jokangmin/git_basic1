package member.service;

import java.util.Scanner;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class WriteService implements Member{
	Scanner scan = new Scanner(System.in);
	MemberDAO memberDAO = MemberDAO.getInstance();
	MemberDTO memberDTO = new MemberDTO();
	private String id;
	@Override
	public void execute() {
		System.out.print("이름 입력 : ");
		String name = scan.next();
		while(true) {
			System.out.print("아이디 입력 : ");
			id = scan.next();
			int state = memberDAO.isExistId(memberDTO, id);
			if(state == 1) {
				System.out.println("사용중인 아이디 입니다.");
				state = 0;
			}
			else break;
		}
		/*
		 boolean exist = false;
		 do{
		 	System.out.print("이름 입력 : ");
			String name = scan.next();
		 }
		 while(exist){
		 	System.out.print("아이디 입력 : ");
			id = scan.next();
			menbetDAO.isExist(Id);
			if(exist){
				System.out.println("사용중인 아이디 입니다.");
			}
			else{
				System.out.println("사용가능한 아이디 입니다.");
				break;
			}
		 }
		 */
		System.out.print("비밀번호 입력 : ");
		String pwd = scan.next();
		System.out.print("핸드폰 번호 입력 (010-1234-5678): ");
		String phone = scan.next();
		MemberDTO memberDTO = new MemberDTO(name, id, pwd, phone);
		//MemberDAO memberDAO = new MemberDAO(); 이렇게 하면 계속해서 새로운 주소이므로 나중에 죽는다.
		int su = memberDAO.write(memberDTO);
		System.out.println(su+"번 입력되었습니다.");
		System.out.println("회원가입 완료");
		
		
		
	}

}
