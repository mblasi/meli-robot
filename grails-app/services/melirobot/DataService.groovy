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
				image = "shoe.jpg"
				stock_qty = 1
				published = false
				price = 200
	        }
                unused {
        	    _id = 2 
        	    name = "racket"
				description = "tennis racket"
				image = "racket.jpg"
				stock_qty = 3
				published = true
				price = 800
        	}
        	unused {
	            _id = 3 
	            name = "pen"
				description = "blue pen"
				image = "pen.jpg"
				stock_qty = 50
				published = false
				price = 130
	        }
                unused {
	            _id = 4 
	            name = "xbox"
				description = "better than wii"
				image = "xbox.jpg"
				stock_qty = 2
				published = true
				price = 2000
	        }
                unused {
	            _id = 5 
	            name = "ball"
				description = "huge ball"
				image = "ball.jpg"
				stock_qty = 12
				published = false
				price = 50
	        }
	    }
	})
    }
}
