package com.echain.web.cmcc;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

public class JSEncryptUtil {
	
	private static Invocable invocableEngine;
	
	static {
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engie = manager.getEngineByName("js");
        try {
			engie.eval(new FileReader(JSEncryptUtil.class.getResource("login.10086.js").getPath()));
		} catch (Exception e) {}
        invocableEngine = (Invocable) engie;
	}
	
	public static String encrypt(String pwd) {
		if(invocableEngine == null  || StringUtils.isBlank(pwd)) {
			return null;
		}
		try {
			return (String)invocableEngine.invokeFunction("encrypt",pwd);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		System.out.println(JSEncryptUtil.class.getResource("/script/10086.js").getPath());
//		if(true) {
//			return;
//		}
		//
//		EbVb04CLW5A6dNj18Kbw9f1a2UHv4tJaC4t29qzJTPsmLwPbYwDd8Gaj7pUDKPWU9m2sqMoidGyj1MDpMs1/RB/XTuFpM0fWnvpRQwnSJHhhl+PLlH5RvPydlUYAiK/qemra4Y7foS4ep9LF1HTEBHk28nLPnZe+wZhxf4E7u09+bfwnq/5EideGqHPCY+mwI8NUyGyiPlYzLT3uWisgdcOXtZITvhY1MUbcjB8SSh9nRx2ER9sMRRN+fwd++VbEifx+8bB9cyo+qe6qXBagz0Uw87tmNJxIBRvaB32AS/gTv91iLVY7ERS0IktJ+OzFQJKkCR4knPnphfTIlNjHnA==
//		FljIjw65pW1zskqnSC1GQiunMdHcUONM++6hVEaK8RClOOpeFQupwhNVhlvM3E3ZGe9QiDhFqTl2axdWotal5Qu1eg6vgmkM9zRbduk+G7aWtLftBr6D3K0cdJuKfbX2/N39gx9NIgIgqITFRdFaXc9Q9v/deUeKRJ4cFqo3NlybrFTT6MC0N/liYGz0Qdo42byouSQoQGMFkW9NAVCmOiaK6XijpVWltBUdNqVcEzVDViSAolLeN/tJtB3Lx6CMY6o/yMx9Fmn06tdzdYms6cu3Ois4gDjX0zXvxAZVd5xSR7RVl6lrZ8ze4C0MkbGrwc6NOcicibi1wIYzlU/wrQ==
		
		
		
//		lC+4vx/LXT2Ln1wSLfRgm7fhhBik9/QdjuIgwwf/I15ruj2z0im0Wu13N4omk2yn7nODB1nx8YYMVDz9SFyRw2F9M+MeUy0WShmbxI/O/L+1/MwIVWBAzml9wXo7iJYyJN8l+chhgaAi0MwvHm+xDt7ostUF4kJus+kMsAqDKvo7Kd4v0DH3YG+4MicQOf/nd1bBcfPR89NGV4o5N3VDA2QftCT93s7pA0Aj08I1oP1LULvdEztP0KzF02WP7YMIPOdAHabV4k1FxS4uL4K86poJuL+TS58vU+fWv0awEq7mbE0g3GmoeivH9UHMEZQ4bj92oYJmQXaOrdeJs3AxrQ==
//		WC7VBeKfRDoFUoyOsrQcc69c1VP8JYSAEjBSA/wtgQYTxSyNUqppkym7HLM0nURpxMz4r9TbeVwj4cqHz65yHKLYzDZxDNC0ilg2Bk50LhfrJvAReuVUMeOPoRGuFYPHeaQzjGFSsF7rLqjgsgCQmpKkVN9ojli640AZZgcgHbY7oR1E46ezlpPVLHzNe9Q7Mcm4TfXd9AD/KP9SyKbQpMMMNHZf5xitbHFjZwC8LztZIjG2oftCZD9OMYoFOTmrHd8kTN/sw66C5tF/zRoyVf5woyfb2j9lefQ9GqrWFiW/gZ+bQ9ub+nQ2Ysm9+ucn1gGf3Om1gu3m5EIfALf/uQ==
		
        
		for(int i=0;i<20;i++) {
			System.out.println(JSEncryptUtil.encrypt("150010"));
			System.out.println(System.currentTimeMillis()/1000);
		}
	}
}