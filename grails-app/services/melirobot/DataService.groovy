package melirobot

import grails.web.JSONBuilder
import grails.converters.JSON

class DataService {

	//TODO save in local database the relation between two products
	static def publications = ["1": false, "2": false, "3": false, "4": false, "5": false]

    def findAllLocal() {
		//TODO retrieve from local endpoint
        def json = new JSONBuilder()

        return JSON.parse(json.build (
	{
	    array {
	        unused {
	            _id = 1
	            name = "shoe"
				description = "just left shoe"
				image = "shoe.jpg"
				stock_qty = 1
				price = 200
	        }
                unused {
        	    _id = 2 
        	    name = "racket"
				description = "tennis racket"
				image = "racket.jpg"
				stock_qty = 3
				price = 800
        	}
        	unused {
	            _id = 3 
	            name = "pen"
				description = "blue pen"
				image = "pen.jpg"
				stock_qty = 50
				price = 130
	        }
                unused {
	            _id = 4 
	            name = "xbox"
				description = "better than wii"
				image = "xbox.jpg"
				stock_qty = 2
				price = 2000
	        }
                unused {
	            _id = 5 
	            name = "ball"
				description = "huge ball"
				image = "ball.jpg"
				stock_qty = 12
				price = 50
	        }
	    }
	}).toString())
    }

	def findById(id) {
		def ret
		findAllLocal().each() {prod -> if(prod._id == id) ret = prod}
		return ret
	}

	def meliId(product) {
        return publications["" + product._id]
	}

	def isPublished(product) {
		return meliId(product) != false
	}

	def linkProducts(product, meliId) {
		publications["" + product._id] = meliId
	}
}
