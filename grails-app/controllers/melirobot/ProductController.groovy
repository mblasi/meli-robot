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
        def data = dataService.findAllLocal()

		def products = new ArrayList()
		data.each() {prod -> products << new Product(prod)}

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

	def publish(int id) {
		def product = dataService.findById(id)

		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());

		def image = grailsApplication.config.folders.images + '/' + product.image
		def imageUrl = imageService.postImage(image, m.getAccessToken())

		def jsonData = new JSONBuilder().build (
			  {
				  title = product.name
				  description = product.description
				  available_quantity = product.stock_qty
				  price = product.price
				  category_id = "MLA86029" //Accesorios para autos - Otros TODO: allow category selection
				  currency_id = "ARS"
				  buying_mode = "buy_it_now"
				  listing_type_id = "bronze"
				  condition = "new"
				  seller_custom_field = product._id
				  pictures = [{source = imageUrl}]
			  }
		)

		Response response = m.post("/items", params, jsonData.toString());

		def publication = JSON.parse(response.getResponseBody())

		if(publication.error == null) {
		    dataService.linkProducts(product, publication.id)
//			def response2 = m.post("/items/" + publication.id + "/pictures", params, '{"id": "' + imageId + '"}')
//			publication = JSON.parse(response2.getResponseBody())
		    redirect action: 'list'
		} else {
		    throw new Exception(publication.error)
		}
	}
}