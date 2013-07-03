# Ratpack Track MY Stuff App - NO DB

A simple Groovy/Ratpack CRUD application using the simple GStorm ORM (https://github.com/kdabir/gstorm) and HSQLDB (http://hsqldb.org/) as the database.
This is a port of a version I created previously using Sinatra/Ruby.

WARNING: GStorm is a new project and might change. I actually used the 0.3/0.4 versions

This was created as a learning and proof-of-concept project for BOTH Groovy and Ratpack, and is not expected to be beautiful by any means.

KNOWN BUG: For the update and delete options, the icons "disappear", as the default route is not invoked (or the page is not being redirected to it).
I have to figure out the best way to do this, but for now, you can just go to the localhost:5050 page and it will reload the icons.

As, I said, this is a learning tool and is a works-in-progress.

Also, I am not closing the connection right now. There is a feature request in Ratpack for a shutdown hook so this can be done:

https://github.com/ratpack/ratpack/issues/67

ratpack.groovy is the main app/script

I have also included a Main.groovy which is a Groovy Application that loads the Groovy scripts

NOTE: Because the GStorm verson that Groovy Grapes will grab was built with a different version of Java than what I have installed,
I built the jar file locally and start with a batch file (yes, I'm using windows, so what???) run_app.bat.
This can easily be modified for a shell script.

This batch file includes commands that will invoke the groovy script (ratpack.groovy), as well as the Main.groovy.
For using the Main.groovy, I placed all the jar files required for runtime in a /lib folder.

I have included a groovy script to pre-populate the database named PopulateDb.groovy.
This script will drop the table if it exists and create the database and pre-populate it with a few items.

I know, I know, there aren't any tests. I'll add some later. I'm still learning Groovy and Ratpack and haven't yet learned Groovy-style testing.

Also, feel free to use this as you see fit, but BE WARNED this isn't really production-ready code and use it at your own risk.

You have been warned!!!

NOTE:

This is based upon several resources I discovered on the internet.
I will add resource links links when I update the project.

For now see:

http://www.ratpack-framework.org/

for details on the Ratpack Framework.

and:

https://github.com/kdabir/gstorm

for details on the GStorm ORM.

