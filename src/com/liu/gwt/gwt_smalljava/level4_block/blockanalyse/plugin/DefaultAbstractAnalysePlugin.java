package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

/**
 * ���������������װ����������������Plugin������ʹ��
 * @author liujunsong
 *
 */
public abstract class DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	public  String _trimReturnAndSpace(String strinput) {
		// String sout = "";
		// �Ȳ��ҵ�һ������\r\n \r �ո��λ��
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// û���ҵ���Ч�ַ�
			return "";
		}

		// ��ʼ�Ӻ���ǰ���ҵ�һ����Ч�ַ�
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

		// ����ipos��Ч������ipos2һ��Ҳ����Ч��
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			System.out.println("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

	// ���ַ��������ҵ���һ�н�����,ͨ������\r��ʵ��
	public int _getFirstLineEndPos(String s1) {
		int ipos = 0;
		for (ipos = 0; ipos < s1.length(); ipos++) {
			if (s1.charAt(ipos) == '\r') {
				return ipos;
			}
		}
		// ���ѭ�����Ҳ�Ҳ��������㣬˵��û�лس���
		return -1;
	}
	
	// -1����û���ҵ�
	// ��������ʱ���ڿ���̨��ӡ��������Ϣ��������ʱ���׳��쳣
	// ��������ʱ�ṩ���ַ��������㷨
	// �������£�
	// 1. ������������
	// 2.˫����������
	// 3.����������
	// 4 {}������
	// 5 todo: ����ע�͵��ַ���
	public int _findfirstStringForBlock(String s1, String s2) {
		// NULLֵ���
		if (s1 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is null");
			return -1;
		}

		if (s2 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is null");
			return -1;
		}

		// empty string ���
		if (s1.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is empty");
			return -1;
		}

		// ����˫���ź͵����ŵļ�����
		int doublequotecount = 0;
		int singlequotecount = 0;

		// ���������ţ������ŵļ�����
		// �������ڵ���������������ų��⡿���������
		int leftParenthesescount = 0;
		int rightParenthesescount = 0;

		// ����������ţ��Ҵ����ŵļ�����
		int leftBracketcount = 0;
		int rightBracketcount = 0;

		// ���忪ʼ����λ��
		int ibeginpos = 0;
		// �����������λ��
		int iendpos = s1.length() - s2.length();

		int ipos;
		for (ipos = ibeginpos; ipos <= iendpos; ipos++) {
			// �����ǰλ����ת�����������һ�ַ�
			if (s1.substring(ipos, ipos + 1).equals("\\")) {
				ipos++;
				// ������ǰ�ַ�������ѭ������ѭ����ֹ������������һ�ַ�
				continue;
			}

			// �жϵ�ǰλ���Ƿ��ǵ����Ż���˫���ţ�����ǣ��������+1
			if (s1.substring(ipos, ipos + 1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos + 1).equals("\"")) {
				doublequotecount++;
			}

			// ��������ţ�˫���ž��Ѿ��պϣ�������ַ�������ж�
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				// �����Ű���֮�⣬���������Ž��м���
				if (s1.substring(ipos, ipos + 1).equals("(")) {
					leftParenthesescount++;
				}
				if (s1.substring(ipos, ipos + 1).equals(")")) {
					rightParenthesescount++;
				}
				if (s1.substring(ipos, ipos + 1).equals("{")) {
					leftBracketcount++;
				}
				if (s1.substring(ipos, ipos + 1).equals("}")) {
					rightBracketcount++;
				}

				// �����ǰ����ģʽ�������ڣ���ô�Ͳ�����ͱȽ�
				if (leftParenthesescount != rightParenthesescount) {
					// ��һ���������ǿ��Է��ص�
					if (leftParenthesescount == 1 && rightParenthesescount == 0 && s2.equals("(")) {
						return ipos;
					}
					continue;
				}

				// �����ǰ����ģʽ�ڴ������ڣ���ô�Ͳ�����ͱȽ�
				if (leftBracketcount != rightBracketcount) {
					// ��һ����������ǿ��Է��ص�
					if (rightBracketcount == 0 && leftBracketcount == 1 && s2.equals("{")) {
						return ipos;
					}
					continue;
				}

				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					// �ҵ�������ַ���,���ش�λ��
					// TODO���˴���Ҫ��������жϣ�ȷ����ǰ���ҵ���λ����һ��������λ�ýڵ㣬������һ��������һ����
					// ����Ҫ���� and ,��ôҪ�ų�land,hand,and1 ֮��
					// ��˿��ܻ�����Ҫ��ԭʼ�ַ����Ƚ���һ�ηִʲ�������ȷ���ҵ���λ����һ����Чλ��
					// �ⲿ�ִ������һ�����䡣

					// add by liujunsong 2021/03/28
					// �жϵ�ǰλ��֮ǰ�ǲ����ַ���������
					// ������ַ������֣������������ɷ���
					// ������{;
					if (ipos > 0 && !s2.equals(";") && !s2.equals("{") && !s2.equals(")") && !s2.equals("}")) {
						// ȡһ���ַ�
						char leftchar = s1.charAt(ipos - 1);
						if (leftchar >= '0' && leftchar <= '9') {
							// �������ֵ��������
							continue;
						}
						if (leftchar >= 'a' && leftchar <= 'z') {
							// �����Сд��ĸ��������
							continue;
						}
						if (leftchar >= 'A' && leftchar <= 'Z') {
							// ����Ǵ�д��ĸ��������
							continue;
						}
					}

					// �жϺ�����ĸ�Ƿ������ֺ���ĸ��������
					// ������;{
					if (ipos < s1.length() - s2.length() - 1 && !s2.equals(";") && !s2.equals("{") && !s2.equals("}")) {
						// ȡһ���ַ�
						char rightChar = s1.charAt(ipos + s2.length());
						System.out.println("rightChar is:" + rightChar);
						if (rightChar >= '0' && rightChar <= '9') {
							// �������ֵ��������
							continue;
						}
						if (rightChar >= 'a' && rightChar <= 'z') {
							// �����Сд��ĸ��������
							continue;
						}
						if (rightChar >= 'A' && rightChar <= 'Z') {
							// ����Ǵ�д��ĸ��������
							continue;
						}
					}
					return ipos;
				}
			}
			// û���ҵ��ַ�����ѭ���ȴ�
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}	
	
	public void log(String sinfo) {
		System.out.println(sinfo);
	}
}
