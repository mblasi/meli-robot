package melirobot

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import org.apache.commons.io.IOUtils
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.ByteArrayBody

@Transactional
class ImageService {

  def servletContext

  def postImage(image, publication, token) {

    def content = IOUtils.toByteArray(servletContext.getResourceAsStream(image));
//
//    withHttp(uri: "https://api.mercadolibre.com/items/") {
//
////	  def entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)
////	  entity.addPart("file", new ByteArrayBody(content, image))
//
//      return post(path : '/' + publication.id + '/pictures', query: [access_token: token], requestContentType: "multipart/form-data", body : content )
//    }

	  try {
		  def ret = null
		  def http = new HTTPBuilder("https://api.mercadolibre.com/")

		  // perform a POST request, expecting JSON response
		  http.request(Method.POST, ContentType.JSON) {
			  uri.path = '/pictures'
			  uri.query = [access_token: token]
			  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

			  requestContentType = "multipart/form-data"
			  def entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)
			  entity.addPart("file", new ByteArrayBody(content, image))
			  request.entity = entity
			  // response handler for a success response code
			  response.success = { resp, reader ->
				  ret = reader.get("id")
			  }
		  }
		  return ret

	  } catch (groovyx.net.http.HttpResponseException ex) {
		  ex.printStackTrace()
		  return null
	  } catch (java.net.ConnectException ex) {
		  ex.printStackTrace()
		  return null
	  }
  }
}