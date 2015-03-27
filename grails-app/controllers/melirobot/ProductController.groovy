package melirobot

import grails.converters.JSON
import grails.web.JSONBuilder

import com.mercadolibre.sdk.Meli
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

class ProductController {

    def dataService
	def imageService
	def grailsApplication
	
	//TODO not a nice place to put this
	def m = new Meli(1742609979742013, "ur4ZXJQwH3SFbvM0fQMAwL9QXNH49sSD")
	
    def index() {
        render 'hello world'
    }

    def list() {
        def data = dataService.findAllFromAxis()
		
		def products = new ArrayList()

//		def i = 0
		data.each() {prod -> 
//			if (i++ < 4) {
			def prodNode = dataService.findProduct(prod.@id)
			def attrs = prodNode.attributes()
			products << new Product(prodNode)
//			}
		}
		
        render (view: 'list', model: [products: products])
    }

	def authenticate() {
		//TODO this url has to be the same than (2)!
		String redirectUrl = m.getAuthUrl("http://localhost:8080/melirobot/product/authorize"); //(1)

		System.out << "redirectUrl: " + redirectUrl

		redirect url: redirectUrl
	}

	def authorize(String code) {
		//TODO this url has to be the same than (1)!
		System.out << "Authenticated and authorized: " + code
		m.authorize(code, "http://localhost:8080/melirobot/product/authorize"); //(2)
		//TODO What about the redirect url configured in the ML app??? (just the hostname is validated)
		register()
		redirect action: 'list'
	}

	def register() {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		def response = m.get("/users/me", params);
		def data = response.getResponseBody()
		def jsonData = JSON.parse(data)
		session["user"] = jsonData.nickname
	}

	def logout() {
		session["user"] = null
		session.invalidate()
		
		redirect action: 'list'
	}

	def publish() {
		def image = params.productImage
		FluentStringsMap paramsMap = new FluentStringsMap();
		paramsMap.add("access_token", m.getAccessToken());

		def jsonData = new JSONBuilder().build (
			  {
				  title = params.productName
				  description = params.productDescription
				  available_quantity = params.productStock
				  price = params.productPrice
				  category_id = "MLA86029" //Accesorios para autos - Otros TODO: allow category selection
				  currency_id = "ARS"
				  buying_mode = "buy_it_now"
				  listing_type_id = "bronze"
				  condition = "new"
				  seller_custom_field = params.productId
				  pictures = [{source = image}]
			  }
		)

		Response response = m.post("/items", paramsMap, jsonData.toString());

		def publication = JSON.parse(response.getResponseBody())

		if(publication.error == null) {
		    dataService.linkProducts(params.productId, publication.id)
		    redirect action: 'list'
		} else {
		    throw new Exception(publication.error)
		}
	}
}
