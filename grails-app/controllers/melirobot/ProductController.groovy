package melirobot

import grails.web.JSONBuilder
import grails.converters.JSON

import com.mercadolibre.sdk.Meli
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response;

class ProductController {

    def dataService

	//TODO not a nice place to put this
	def m = new Meli(1742609979742013, "ur4ZXJQwH3SFbvM0fQMAwL9QXNH49sSD")
	
    def index() {
        render 'hello world'
    }

    def list() {
        def data = dataService.findAllLocal()

		def products = new ArrayList()
		data.each() {prod -> products << new Product(prod)}

		//TODO: it would be better to render each product with a small template, using 'render collection'
        render (view: 'list', model: [products: products])
    }

	def authenticate() {
		//TODO should this url be the same than (2)?
		String redirectUrl = m.getAuthUrl("http://localhost:8080/melirobot/product/authorize"); //(1)
		redirect url: redirectUrl
	}

	def authorize(String code) {
		//TODO should this url be the same than (1)?
		m.authorize(code, "http://localhost:8080/melirobot/product/authorize"); //(2) -> also, this redirect is not being done! I have to continue programaticaly!
		//TODO What about the redirect url configured in the ML app??? it is not being used!
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

		def json = new JSONBuilder()

		//TODO include the image to the created product
		def jsonData = json.build (
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
			  }
			)

		//TODO access token gets invalid after this request
		Response response = m.post("/items", params, jsonData.toString());

		def publication = JSON.parse(response.getResponseBody())

		if(publication.error == null) {
		    dataService.linkProducts(product, publication.id)
		    redirect action: 'list'
		} else {
		    throw Exception(publication.error)
		}
	}
}