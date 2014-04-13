// WARNING!
//
// This doesn't actually work any more, since OAuth is needed.

TwitterAccount {
	//
	// Doesn't work in Windows.
	//
	// Usage:
	//
	// t = TwitterAccount(YOURUSERNAME,YOURPASSWORD);
	// t.tweet("Tweeting from Supercollider");
	//
	var <>username, <>password, <result="";
	
	*initClass {
		Class.initClassTree(String);
	}
	
	*new {
		arg username, password;
		^super.newCopyArgs(username,password).init;
	}
	
	init {
	}
	
	tweet {
		arg text;
		var command, pipe, line;
		
		// Form shell command.
		command = format("curl -u %:% -d status='%' http://twitter.com/statuses/update.xml", username, password, text);
		// Run command.
		pipe = Pipe.new(command, "r");
		// Reset result string.
		result = "";
		// Store result.
		//result = pipe.contents; // This doesn't work as readAllString is defined in File instead of UnixFile. Weird?
		line = pipe.getLine;
		while ({line.notNil}, {
			result = result ++ line ++ "\n";
			line = pipe.getLine;
		});
		// Close pipe.
		pipe.close;
	}
}
