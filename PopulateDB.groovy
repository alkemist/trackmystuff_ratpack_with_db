// Script to create and pre-populate the TrackMyStuff database
// This uses precompiled versions of Item and and the GStormFactory classes
//
// ----------------------------------------------------------------------------
//
// NOTE: Because the original GStorm repo is built with a different version of 
// Java than I use, I build my own version of the jar.
//
// Also, because of issues with the script being able to use the classes,
// I pre-compiled the Item and GStormDactory classes
//
// I run this script by adding the jar to the classpath at the command line as 
// follows:
//
// groovy -cp gstorm-master-0.4-dev.jar PopulateDb.groovy

// Imports are not necessaey, as the classes are in the same package
//import Item
//Import GStormFactory

// Get a singleton instance and return the GStorm handle
def gstormInst = GStormFactory.instance.getGstorm()

///////////////////////////////////////////////////////////////////////////////
// In order to prevent duplicate rows, drop the table if it exists
def sql = GStormFactory.instance.getGSql()
sql.execute("DROP TABLE Item IF EXISTS")
///////////////////////////////////////////////////////////////////////////////

gstormInst.stormify(Item) // table automatically gets created for this class

def item = new Item(name: "Groovy in Action", itemType: "Book", 
  itemLocation_1: "Bookshelf 3", itemLocation_2: "Shelf 5", 
  description: "The best guide to learning Groovy",
  lastUpdatedAt: new Date())
item.save()  // save it
  
println "Object for item ${item.name} is ${item.id}"

def otherItem = new Item(name: "Animal House", itemType: "VHS", 
  itemLocation_1: "DVD Case 12", itemLocation_2: "Shelf 2", 
  description: "No Prisoners",
  lastUpdatedAt: new Date()).save() // save one more
println "Created  and saved ${otherItem.name} of type ${otherItem.itemType}"

otherItem.itemType = "DVD"
otherItem.save() // update it

def yetAnotherItem = new Item(name: "Ubuntu 12.04", itemType: "Software", 
  itemLocation_1: "Backup Drive 3", itemLocation_2: "Install Folder", 
  description: "The Penguin Rulz",
  lastUpdatedAt: new Date()).save() // save one more
println "Created and saved ${yetAnotherItem.name} of type " +
  "${yetAnotherItem.itemType}"

println "-".multiply(80)  // print 80 times
println "Display, all the current records"
println "-".multiply(80)

// println "all records -> ${Item.all}"
println "All records:"
itemList = Item.all
itemList.each() {
  println "${it.name}"
}
  
// Close the SQL connection  
GStormFactory.instance.closeConnection();

println 'Done populating the DB'
