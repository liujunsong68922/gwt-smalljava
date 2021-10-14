package com.smalljava.core.common.logging;

public class Logger {
	
	private String _prefix;
	
	public Logger(Class _class) {
		_prefix = _class.getSimpleName();
	}
	
	public Logger(String prefix) {
		this._prefix = prefix;
	}
	
	public String get_prefix() {
		return _prefix;
	}

	public void set_prefix(String _prefix) {
		this._prefix = _prefix;
	}

	public void log(String s) {
		if(_prefix != null) {
			log2(_prefix,s);
		}else {
			log2("",s);
		}

	}
	
	public void log2(String prefix,String s) {
		System.out.println("["+prefix+"]: "+s);
		try {
			consoleLog("["+prefix+"]: "+s);
		}catch(Error e) {
			//catach special error,
			//if this function is unsupported,just return.
		}
	}

	public void info(String string) {
		log(string);
		
	}

	public void error(String string) {
		log(string);
		
	}

	public void debug(String stringcontent) {
		//debug info is closed on default.
		//log(stringcontent);
	}

	public void error(String string, Exception e) {
		System.out.println(string);
		e.printStackTrace();
		
	}
	
	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ExpressionEval]:" + message );
	}-*/;
}
