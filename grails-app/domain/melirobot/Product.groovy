package melirobot

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
}
