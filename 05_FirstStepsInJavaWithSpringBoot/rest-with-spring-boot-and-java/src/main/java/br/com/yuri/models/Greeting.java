package br.com.yuri.models;

@SuppressWarnings("unused")
public class Greeting {

    public final long id;
	public final String content;
	
	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
}
