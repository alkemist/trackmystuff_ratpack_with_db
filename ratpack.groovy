@GrabResolver("https://oss.jfrog.org/artifactory/repo")
@Grab(value = "org.ratpack-framework:ratpack-groovy:0.9.0-SNAPSHOT")

import org.ratpackframework.groovy.templating.TemplateRenderer
import org.ratpackframework.groovy.templating.TemplatingModule
import static org.ratpackframework.groovy.RatpackScript.ratpack

// For session handling - I'm not using it now
//import org.ratpackframework.session.Session
//import org.ratpackframework.session.store.MapSessionsModule
//import org.ratpackframework.session.store.SessionStorage

import java.util.logging.Logger

ratpack {
    
    // Initialize the GStorm ORM Factory
    def gstormInst = GStormFactory.instance.getGstorm()
    gstormInst.stormify(Item) // table automatically gets created for this class

    // TODO: close the SQL connection at shutdown
    // GStormFactory.instance.closeConnection();		
	   
    // TODO: use the logger	
    def logger = Logger.getLogger("")
	
    modules {
        get(TemplatingModule).setCacheSize(0)
		
        // Enable non persistent map based session storage
		// Not using it now
        // register(new MapSessionsModule(10, 5))
    }
    
    handlers {

        // default route
        get {
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"
        }
		
		// Form for creating new entries
		get ("new") {
            get(TemplateRenderer).render "new.html", title: "Groovy Track My Stuff"
		}

		// http://localhost:5050/edit/23
        get("edit/:id"){
		  // TODO: retrieve the item and passs along the info to populate the form
		  
          println "getting item with ID " + pathTokens.id
          def item = Item.get(pathTokens.id)
		  
          get(TemplateRenderer).render "edit.html", id: item.id, name: item.name, itemType: item.itemType,
		    itemLocation_1: item.itemLocation_1, itemLocation_2: item.itemLocation_2, 
			description: item.description
        }		
		
		// http://localhost:5050/delete2
        get("delete/:id"){
		  // TODO: retrieve the item and get the name and any other info
          // get(TemplateRenderer).render "delete.html", id: "${pathTokens.id}", name: ${pathTokens.name}
          get(TemplateRenderer).render "delete.html", id: "${pathTokens.id}"
        }

		// --------------------------------------------------------------------
		// POSTs
		// --------------------------------------------------------------------
		
		// Data posted from a form
		post ("submit") {
			def form = request.form
			
            def item = new Item(name: form.name, itemType: form.item_type, 
              itemLocation_1: form.item_location_1, itemLocation_2: form.item_location_2, 
              description: form.description,
              lastUpdatedAt: new Date())
            item.save()  // save it

            // TODO: use the logger			
			def message = "Just created Item " + item.name + " with id " + item.id
			println message
			
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"
        }									
		
		// http://localhost:5050/update/item/1
		post ("update/item/:id") {
		
			def form = request.form
			
			// println "Updating " + form.name
			
			// Update is a save with an id
            def item = new Item(id: form.id, name: form.name, itemType: form.item_type, 
              itemLocation_1: form.item_location_1, itemLocation_2: form.item_location_2, 
              description: form.description,
              lastUpdatedAt: new Date())
            item.save()  // save it		
		
            // response.send "Now updating item with ID: ${pathTokens.id}"
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"			
        }											
		
		// http://localhost:5050/delete/item/1
		post ("delete/item/:id") {
            //response.send "Now deleting item with ID: ${pathTokens.id}"
			
            // TODO: use the logger			
			println "Now deleting item with ID: ${pathTokens.id}"
			
			// In order to delete an item, you must
			// 1) Retrieve the item
			// 2) Create an new Item instance
			// 3) call delete on the new instance of Item
            def item = Item.get(pathTokens.id)
			def newItem = new Item(id: item.id, name: item.name)
			newItem.delete()
			
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"			
        }									

        assets "public"
    }
}
