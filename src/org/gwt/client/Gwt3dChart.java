package org.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt3dChart implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final Label l=new Label("Hello");
		RootPanel.get("sendButtonContainer").add(l);
		
		/*MyJzy3d m=(MyJzy3d)GWT.create(MyJzy3d.class);
		Widget wApplet=AppletJSUtil.createAppletWidget((Applet)GWT.create(MyJzy3d.class));
		RootPanel.get("sendButtonContainer").add(wApplet);*/
		
		Button but=new Button("Click me");
		but.setFocus(false);
		but.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
						
				
				greetingService.greetServer(new String(""), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						//l.setText(result);
						xmlDataHandler(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						l.setText(caught.toString());
					}
				});
				  				
			}
		});
		
		RootPanel.get("sendButtonContainer").add(but);
		
		
		
		
	}
	protected native void xmlDataHandler(String xmlData) /*-{
	var a = $doc.jzy3d;
	if (a != null) {
	 a.addNewChart(xmlData);
	 a.focus();
	} else {
	 alert("applet not found");
	}
}-*/;
}
