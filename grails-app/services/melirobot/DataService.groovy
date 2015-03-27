package melirobot

import grails.web.JSONBuilder
import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.ContentType
import groovy.util.XmlSlurper
import groovy.util.slurpersupport.NodeChild;

class DataService {

	//TODO save in local database the relation between two products
	static def publications = [:]

	def findAllFromAxis() {

		def process = "curl http://8D1G53A3XCX7TKSMGQPS34ATSPMEWET5@www.axis.com.ar/api/products".execute()
		process.waitFor()
		def text = process.text
        def prodList = new XmlSlurper().parseText(text)

		return prodList.products.product

		//TODO a clean way to do this, but doesn't work:
//		def http = new HTTPBuilder("http://8D1G53A3XCX7TKSMGQPS34ATSPMEWET5@www.axis.com.ar/api/products")
//		http.auth.basic '8D1G53A3XCX7TKSMGQPS34ATSPMEWET5', ''

//	try{
//		def products = http.get(headers: [Accept: 'application/xml']) { resp, xml ->
//			System.out << 'status: ' + resp.status
//			System.out << 'xml: ' + xml
//			return xml
//		}
//		System.out << "get: " + products

//	} catch (groovyx.net.http.HttpResponseException ex) {
//		ex.printStackTrace()
//		return null
//	} catch (java.net.ConnectException ex) {
//		ex.printStackTrace()
//		return null
//	}
	}
	
	def findProduct(def _id) {
		def process = ("curl http://8D1G53A3XCX7TKSMGQPS34ATSPMEWET5@www.axis.com.ar/api/products/" + _id).execute()
		process.waitFor()
		return new XmlSlurper().parseText(process.text).product[0]
	}

	def meliId(product) {
        return publications.get("" + product._id)
	}

	def isPublished(product) {
		def published = false
		if (meliId(product) != null) {
			published = true
		}
		return published
	}

	def linkProducts(productId, meliId) {
		publications.put(productId, meliId)
	}
}
