package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.MethodBlock;

public class MethodPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		if(rootblock.computestring.startsWith("public")
				|| rootblock.computestring.startsWith("private")) {
			MethodBlock methodblock = new MethodBlock("MethodBlock","",rootblock);
			
			int ipos1 = this._findfirstStringForBlock(rootblock.computestring, "{");
			int ipos2 = this._findfirstStringForBlock(rootblock.computestring, "}");
			//TODO���жϺϷ���
			String methodcontent = rootblock.computestring.substring(ipos1,ipos2+1);
			methodblock.setMethodcontent(methodcontent);
			//��ǰ��Ķ����������method����
			String leftstring = rootblock.computestring.substring(0,ipos1);
			//ȥ��public ,private �ؼ���
			if (leftstring.startsWith("public ")) {
				leftstring = leftstring.substring(7);
				leftstring = this._trimReturnAndSpace(leftstring);
			}
			if( leftstring.startsWith("private ")) {
				leftstring = leftstring.substring(8);
				leftstring = this._trimReturnAndSpace(leftstring);				
			}
			
			//����static �ؼ���
			leftstring = leftstring.replaceAll("static", "");
			leftstring = this._trimReturnAndSpace(leftstring);
			
			//���տո���зָ�
			int ipos3 = leftstring.indexOf(" ");
			String strreturntype = leftstring.substring(0,ipos3);
			strreturntype = this._trimReturnAndSpace(strreturntype);
			//д�뷵������
			methodblock.setReturntype(strreturntype);
			
			leftstring = leftstring.substring(ipos3+1);
			leftstring = this._trimReturnAndSpace(leftstring);
			
			int ipos4 = this._findfirstStringForBlock(leftstring, "(");
			int ipos5 = this._findfirstStringForBlock(leftstring, ")");
			//TODO:�жϺϷ���
			String methodname = leftstring.substring(0,ipos4);
			methodname = this._trimReturnAndSpace(methodname);
			methodblock.setMethodname(methodname);
			
			//���������������
			String args = "";
			if(ipos5>ipos4+1) {
				args = leftstring.substring(ipos4+1,ipos5);
			}
			if(args.length()>0) {
				String[] argarray = args.split(",");
				for(String argexp:argarray) {
					//TODO:�жϱ�������Ч��
					String[] var = argexp.split(" ");
					if(var.length==2) {
						String vartype = var[0];
						String varname = var[1];
						methodblock.getArgs().put(varname, vartype);
					}
				}
			}
			
			//��method�Ķ���д�뵽methodList()����ȡ
			rootblock.getMethodList().add(methodblock);
			rootblock.getChildren().add(methodblock);
			rootblock.computestring = rootblock.computestring.substring(ipos2+1);
			return true;
			
		}
		return true;
	}

//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
