package org.cloudfoundry.autoscaler.rest.mock.couchdb;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cloudfoundry.autoscaler.util.RestApiResponseHandler;
import org.json.JSONObject;

@Path("/couchdb-scalingmetric-continuously")
public class CouchDBScalingMetricRestApi {
	//_design/Application_ByAppId/_view/by_appId?key=%221b96f579-29f5-470c-84cc-03dd6ddddb65%22&include_docs=true
	@GET
	@Path("_design/{designDocType}/_view/{viewName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocument(@Context final HttpServletRequest httpServletRequest,
			@PathParam("designDocType") final String designDocType,@PathParam("viewName") final String viewName,@QueryParam("key") final String key, @QueryParam("include_docs") final String include_docs) {
		String result = this.getResponse(designDocType, viewName,include_docs);
		return RestApiResponseHandler.getResponseOk(result);

	}
	@GET
	@Path("_design/{designDocType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDesignDocument(@Context final HttpServletRequest httpServletRequest,
			@PathParam("designDocType") final String designDocType) {
		String result = this.getResponse(designDocType);
		return RestApiResponseHandler.getResponseOk(result);

	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDBs(@Context final HttpServletRequest httpServletRequest) {
		String result = "{\"db_name\":\"couchdb-scalingmetric-continuously\",\"doc_count\":71,\"doc_del_count\":52,\"update_seq\":427,\"purge_seq\":0,\"compact_running\":false,\"disk_size\":1327211,\"data_size\":56286,\"instance_start_time\":\"1458806210525541\",\"disk_format_version\":6,\"committed_update_seq\":427}";
		return RestApiResponseHandler.getResponseOk(result);

	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDocument(@Context final HttpServletRequest httpServletRequest,
			 String jsonString) {

//		String jsonStr = "{\"ok\": true,\"id\": \"2d642054-5675-44b2-9304-af58b1648365\",\"rev\": \"2-24c637da3fa1164b1a5fe05a35456e1c\"}";
		
		String jsonStr = CouchDBDocumentManager.getInstance().addDocument(jsonString).toString();
		return RestApiResponseHandler.getResponse(201, jsonStr);

	}

	@DELETE
	@Path("/{docId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpacesApplications(@Context final HttpServletRequest httpServletRequest,
			@PathParam("docId") String docId, @QueryParam("rev") final String rev) {
		String jsonStr = "{\"ok\": true,\"id\": \"2d642054-5675-44b2-9304-af58b1648365\",\"rev\": \"3-24c637da3fa1164b1a5fe05a35456e1c\"}";
		return RestApiResponseHandler.getResponseOk(jsonStr);

	}

	@PUT
	@Path("/{docId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateApplicationInstances(@Context final HttpServletRequest httpServletRequest,
			@PathParam("docId") String docId, String jsonString) {
		
		String returnStr = "{\"ok\": true,\"id\": \"2d642054-5675-44b2-9304-af58b1648365\",\"rev\": \"2-24c637da3fa1164b1a5fe05a35456e1c\"}";
		return RestApiResponseHandler.getResponse(201, returnStr);

	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDB(@Context final HttpServletRequest httpServletRequest,
			@PathParam("docId") String docId, String jsonString) {
		String returnStr = "{\"ok\":true}";
		return RestApiResponseHandler.getResponse(201, returnStr);

	}
	@PUT
	@Path("/_design/{designDocName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createView(@Context final HttpServletRequest httpServletRequest,
			@PathParam("designDocName") String designDocName, String jsonString) {
		String returnStr = "{\"ok\": true,\"id\": \"2d642054-5675-44b2-9304-af58b1648365\",\"rev\": \"2-24c637da3fa1164b1a5fe05a35456e1c\"}";
		return RestApiResponseHandler.getResponse(201, returnStr);

	}
	private String getResponse(String designDocType){
		return "{\"_id\":\"_design/TriggerRecord_byAll\",\"_rev\":\"1-01b42ba750d48b718e244e9390ce640b\",\"views\":{\"byAll\":{\"map\":\"function(doc) { if (doc.type == 'TriggerRecord' ) emit( [doc.appId, doc.serverName], doc._id )}\"}},\"lists\":{},\"shows\":{},\"language\":\"javascript\",\"filters\":{},\"updates\":{}}";
	}

	private String getResponse(String documentId, String viewName,String inclodeDocs){
		CouchDBDocumentManager manager = CouchDBDocumentManager.getInstance();
		return manager.getDocument(documentId,inclodeDocs).toString();
	}

}
