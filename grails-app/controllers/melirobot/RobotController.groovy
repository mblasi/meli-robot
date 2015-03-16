package melirobot

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray

class RobotController {

    def dataService

    def index() {
        render 'hello world'
    }

    def list() {
        def data = JSON.parse(dataService.findAllLocal().toString())
	System.out << data

	def products = new ArrayList()
	data.each() {prod -> products << new Product(prod)}

        render (view: 'list', model: [products: products])
    }
}
