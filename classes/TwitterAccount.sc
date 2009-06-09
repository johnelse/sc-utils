TwitterAccount {
	//
	// I assume this doesn't work in Windows, due to use of String.unixCmd
	// Will have to test if it works with Cygwin.
	//
	// Usage:
	//
	// t = TwitterAccount(YOURUSERNAME,YOURPASSWORD);
	// t.tweet("Tweeting from Supercollider");
	//
	var <username, <password;
	var command;
	
	*initClass {
		Class.initClassTree(String);
	}
	
	*new {
		arg username, password;
		^super.newCopyArgs(username,password).init;
	}
	
	init {
	}
	
	username_ {
		arg newVal;
		username = newVal;
	}
	
	password_ {
		arg newVal;
		username = newVal;
	}
	
	tweet {
		arg text;
		
		command = format("curl -u %:% -d status='%' http://twitter.com/statuses/update.xml",
			username, password, text);
		
		command.unixCmd;		
	}
}