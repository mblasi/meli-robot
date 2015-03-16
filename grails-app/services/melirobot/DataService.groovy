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
	        }
                unused {
        	    _id = 2 
        	    name = "racket"
        	}
        	unused {
	            _id = 3 
	            name = "pen"
	        }
                unused {
	            _id = 4 
	            name = "xbox"
	        }
                unused {
	            _id = 5 
	            name = "ball"
	        }
	    }
	})
    }
}
