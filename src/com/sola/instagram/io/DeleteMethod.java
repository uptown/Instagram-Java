package com.sola.instagram.io;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.HttpResponse;

import java.io.InputStream;

public class DeleteMethod extends APIMethod {
	
	public DeleteMethod() {
		super();
	}
	
	public DeleteMethod(String methodUri) {
		super(methodUri);
		this.type = "GET";
	}		
	
	@Override
	protected InputStream performRequest() throws Exception {
		HttpResponse response;
		HttpDelete delete = new HttpDelete(this.methodUri);
		InputStream stream = null;
		response = client.execute(delete);
		stream = response.getEntity().getContent();
		return stream;
	}

}
