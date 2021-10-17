package com.smalljava.core.common;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;



public class StringFindUtil {
	private Logger logger = LoggerFactory.getLogger(StringFindUtil.class);
	// -1 means not found
	public int findfirstStringForAST(String s1, String s2) {
		// NULL check
		if (s1 == null) {
			logger.error("ERROR: StringFindUtil.findfirstString,s1 is null");
			return -1;
		}

		if (s2 == null) {
			logger.error("ERROR: StringFindUtil.findfirstString,s2 is null");
			return -1;
		}

		// empty string check
		if (s1.equals("")) {
			logger.error("ERROR: StringFindUtil.findfirstString,s1 is empty");
			return -1;            
		}

		if (s2.equals("")) {
			logger.error("ERROR: StringFindUtil.findfirstString,s2 is empty");
			return -1;
		}

		// " '
		int doublequotecount = 0;
		int singlequotecount = 0;
		
		// { }
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		// 
		int ibeginpos = 0;
		// 
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos <= iendpos; ipos++) {
			//
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				// 
				continue;
			}

			// 
			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			//
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				//
				if(s1.substring(ipos, ipos+1).equals("(")) {
					leftParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals(")")) {
					rightParenthesescount++;
				}
				
				//
				if(leftParenthesescount  != rightParenthesescount) {
					continue;
				}
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					//find it
					return ipos;
				}
			}
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}

	public int findLastStringForAST(String s1, String s2) {
		int ipos1 = this.findfirstStringForAST(s1, s2);
		if(ipos1 ==-1) {
			return -1;
		}else {
			int ipos = ipos1;
			int ipos2=0;
			while(ipos2>=0) {
				System.out.println("s1:"+s1+",s2:"+s2);
				System.out.println("ipos:"+ipos);
				String stemp;
				stemp = s1.substring(ipos+s2.length());
				ipos2 = this.findfirstStringForAST(stemp, s2);
				System.out.println("find ipos2:"+ipos2);
				if(ipos2>0) {
					ipos = ipos + ipos2+s2.length();
				}else {
					break;
				}
			}
			return ipos;
		}
		
	}	
	
	public int findfirstStringForBlock(String s1, String s2) {
		// NULLֵ���
		if (s1 == null) {
			logger.error("ERROR: StringFindUtil.findfirstStringForBlock,s1 is null");
			return -1;
		}

		if (s2 == null) {
			logger.error("ERROR: StringFindUtil.findfirstStringForBlock,s2 is null");
			return -1;
		}

		// empty string 
		if (s1.equals("")) {
			logger.error("ERROR: StringFindUtil.findfirstStringForBlock,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			logger.error("ERROR: StringFindUtil.findfirstStringForBlock,s2 is empty");
			return -1;
		}

		int doublequotecount = 0;
		int singlequotecount = 0;
		
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		int leftBracketcount=0;
		int rightBracketcount=0;
		
		int ibeginpos = 0;
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos <= iendpos; ipos++) {
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				continue;
			}

			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				if(s1.substring(ipos, ipos+1).equals("(")) {
					leftParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals(")")) {
					rightParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals("{")) {
					leftBracketcount++;
				}
				if(s1.substring(ipos, ipos+1).equals("}")) {
					rightBracketcount++;
				}
				
				if(leftParenthesescount  != rightParenthesescount) {
					if(leftParenthesescount==1 && rightParenthesescount==0 && s2.equals("(")) {
						return ipos;
					}
					continue;
				}
				
				if(leftBracketcount != rightBracketcount) {
					if(rightBracketcount==0 && leftBracketcount==1 && s2.equals("{") ) {
						return ipos;
					}
					continue;
				}
				
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					if(ipos>0 && ! s2.equals(";") && !s2.equals("{") && !s2.equals(")")) {
						char leftchar = s1.charAt(ipos-1);
						if(leftchar >='0' && leftchar<='9') {
							continue;
						}
						if(leftchar >='a' && leftchar<='z') {
							continue;
						}
						if(leftchar >='A' && leftchar<='Z') {
							continue;
						}
					}
					
					if(ipos<s1.length()-s2.length()-1 && !s2.equals(";") && !s2.equals("{")) {
						char rightChar = s1.charAt(ipos+s2.length());
						logger.error("rightChar is:"+rightChar);
						if(rightChar >='0' && rightChar<='9') {
							continue;
						}
						if(rightChar >='a' && rightChar<='z') {
							continue;
						}
						if(rightChar >='A' && rightChar<='Z') {
							continue;
						}
					}					
					return ipos;
				}
			}
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}

	public String trimReturnAndSpace(String strinput) {
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			return "";
		}

		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("[ERROR].ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
