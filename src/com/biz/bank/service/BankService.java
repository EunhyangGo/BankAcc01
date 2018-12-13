package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {

	List<BankVO> bankList;
	String strFileName;
	public BankService(String strFileName) {

		bankList = new ArrayList();
		this.strFileName = strFileName;
	}
	public void bankWrite() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(strFileName);
			for(BankVO vo : bankList) {
				
				String strId = vo.getStrId();
				int intBalance = vo.getIntBalance();
				String strLastDate = vo.getStrLastDate();
				
				String wString = String.format("%s:%d:%s",strId, intBalance,strLastDate);

				
				System.out.println(wString);
				pw.println(wString);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
			
		}
	}
	public void readFile() {
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr= new FileReader(strFileName);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String strLine= buffer.readLine();
				if(strLine == null) break;
				String[] st = strLine.split(":");
				// st[0] = id,
				// st[1] = balance,
				// st[2] = lastDate
				BankVO vo = new BankVO();
				vo.setStrId(st[0]);
				vo.setIntBalance(Integer.valueOf(st[1])); //인티져형으로되어있기때문에 인티져형으로 바꿔줘야댐
				vo.setStrLastDate(st[2]);
				bankList.add(vo);
				System.out.println(strLine);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void bankOutput() {
		Scanner sc = new Scanner(System.in);
		
		
			System.out.print("출금 계좌번호 >>");
			String strId = sc.nextLine();
			
			BankVO bankVO = null; //bankVO에는 주소가 들어가있다.
			
			for(BankVO vo : bankList) {
				if(vo.getStrId().equals(strId) ) {
				 bankVO =vo;
				 break;
				}
			}
				if(bankVO == null) {
					System.out.println("없다");
					return;
				//}else {
					//System.out.println("있다");
				} 
				// 원잔액을 추출하고 
				int intBalance = bankVO.getIntBalance();
				
				// 키보드에서 입력 받은 다음에
				System.out.println("출금액을 입력 >> ");
				String strOutput = sc.nextLine();
				int intOutPut = Integer.valueOf(strOutput);
				
				// 현재 시스템의 날짜를 문자열로 가져오기
				// 1.8 이상에서만 작동
				String strDate = LocalDate.now().toString();
				bankVO.setStrLastDate(strDate);
				
				
				// 원잔액과 출금액 비교
				if(intBalance < intOutPut) {
					System.out.println("잔액이 부족하여 출금 못함");
					return;
				}
				
				int intAfterBalance = intBalance - intOutPut; 
				
				bankVO.setIntBalance(intAfterBalance);
				
				System.out.println("======================");
				System.out.println("입금이 완료 되었습니다.");
				System.out.println("----------------------");
				System.out.println("원잔액 : " + intBalance);
				System.out.println("입금액 : " + strOutput);
				System.out.println("현잔액 : " + bankVO.getIntBalance());
				System.out.println("출금액을 입력 >>");
				intBalance = bankVO.getIntBalance();
				
				
	}
	
	public void bankInput() {
		Scanner sc = new Scanner(System.in);
		
		
			System.out.print("계좌번호 >>");
			String strId = sc.nextLine();
			
			BankVO bankVO = null; //bankVO에는 주소가 들어가있다.
			
			for(BankVO vo : bankList) {
				if(vo.getStrId().equals(strId) ) {
				 bankVO =vo;
				 break;
				}
			}
				if(bankVO == null) {
					System.out.println("없다");
					return;
				//}else {
					//System.out.println("있다");
				} 
				// 원잔액을 추출하고 
				int intBalance = bankVO.getIntBalance();
				// 키보드에서 입력 받은 다음에
				System.out.println("입금액을 입력 >> ");
				String strInput = sc.nextLine();
				
				int intAfterBalance = intBalance + Integer.valueOf(strInput);
				
				bankVO.setIntBalance(intAfterBalance);
				
				// 현재 시스템의 날짜를 문자열로 가져오기
				// 1.8 이상에서만 작동
				String strDate = LocalDate.now().toString();
				bankVO.setStrLastDate(strDate);
				
				System.out.println("======================");
				System.out.println("입금이 완료 되었습니다.");
				System.out.println("----------------------");
				System.out.println("원잔액 : " + intBalance);
				System.out.println("입금액 : " + strInput);
				System.out.println("현잔액 : " + bankVO.getIntBalance());
				System.out.println("출금액을 입력 >>");
				intBalance = bankVO.getIntBalance();
			
				
		
			}
	public void total() {
		
		
		System.out.println("+++++++++++++++++++++++");
		System.out.println("계좌번호\t잔액\t최종거래일");
		
		for(BankVO vo : bankList) {
			System.out.printf("%5s\t%10d\t%s\n",
			vo.getStrId(),
			vo.getIntBalance(),
			vo.getStrLastDate());
		
	}
		}
}
		

