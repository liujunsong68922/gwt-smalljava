package com.smalljava.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SmalljavaApp implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
//  private static final String SERVER_ERROR = "An error occurred while "
//      + "attempting to contact the server. Please check your network "
//      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    SmallJavaTestPanel smalljavatestpanel = new SmallJavaTestPanel();
    if(RootPanel.get("smalljavatestpanel") !=null) {
    	RootPanel.get().add(smalljavatestpanel);
    }
    
    SmallJavaWorkflowTestPanel workflowpanel = new SmallJavaWorkflowTestPanel();
    if(RootPanel.get("smalljavaworkflowtestpanel") !=null) {
    	RootPanel.get().add(workflowpanel);
    }
  }
}
