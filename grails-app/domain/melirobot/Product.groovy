package melirobot

import groovy.util.Node
import groovy.util.slurpersupport.NodeChild;

class Product {

    static constraints = {
        // JSON definition of the User object
        grails.converters.JSON.registerObjectMarshaller(Product) {
	     // you can filter here the key-value pairs to output:
         //TODO: research this syntax
	     return it.properties.findAll {k,v -> true}
        }
    }

    int _id
    String name
	String description
	String image
	long stock_qty
	float price

	def Product (def NodeChild node) {
		def qty = node."quantity".text() as long
		if (qty < 1) {
			qty = 1
		}
		_id = node."id".text() as int
		name = node."name"."language"
		description = node."description"."language"
		image = node."id_default_image".@"xlink:href"
		stock_qty = qty
		price = node."price".text() as float
		image = image.replace("www", "8D1G53A3XCX7TKSMGQPS34ATSPMEWET5@www")
	}
}