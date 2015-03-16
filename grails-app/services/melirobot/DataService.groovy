package melirobot

import grails.web.JSONBuilder

class DataService {

    def findAllLocal() {
        def json = new JSONBuilder()

        return json.build (
	{
	    array {
	        unused {
	            _id = 1
	            name = "shoe"
				description = "just left shoe"
				image = ""
				stock_qty = 1
				published = false
	        }
                unused {
        	    _id = 2 
        	    name = "racket"
				description = "tennis racket"
				image = ""
				stock_qty = 3
				published = true
        	}
        	unused {
	            _id = 3 
	            name = "pen"
				description = "blue pen"
				image = ""
				stock_qty = 50
				published = false
	        }
                unused {
	            _id = 4 
	            name = "xbox"
				description = "better than wii"
				image = ""
				stock_qty = 2
				published = true
	        }
                unused {
	            _id = 5 
	            name = "ball"
				description = "huge ball"
				image = ""
				stock_qty = 12
				published = false
	        }
	    }
	})
    }
}
