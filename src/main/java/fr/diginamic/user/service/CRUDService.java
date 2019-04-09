package fr.diginamic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.user.exception.ControllerUserException;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;
import fr.diginamic.user.repository.UserRepository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


//

@Path("/services")
public class Service {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Services> getServices_JSON() {
		List<Services> listOfCountries = EmployeeDAO.getAllEmployees();
		return listOfCountries;
	}
	
	@GET
	@Path("/{empNo}")
	@Produces({ MediaType,APPLICATION_JSON, MediaTpe,APPLICATION_XML})
	public List<Services> getServices_JSON() {
		
	}
}



@Service
public class CRUDService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsences() {
		// le service retourne une liste de ressoure List et un code HTTP 200
		return Response.ok(Absence.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParId(@PathParam("id") int id) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok(Absence.getId(id)).build();
	}
	
	@GET
	@Path("/{startDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParStartDate(@PathParam("startDate") Date startDate) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getStartDate(startDate)).build();
	}

	@GET
	@Path("/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParEndDate(@PathParam("endDate") Date endDate) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getEndDate(endDate)).build();
	}
	
	@GET
	@Path("/{reason}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParReason(@PathParam("reason") String reason) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getReason(reason)).build();
	}
	
	@GET
	@Path("/{idAbsenceType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParIdAbsenceType(@PathParam("idAbsenceType") int idAbsenceType) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getIdAbsenceType(idAbsenceType)).build();
	}
	
	@GET
	@Path("/{idStatus}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParIdStatus(@PathParam("idStatus") int idStatus) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getIdStatus(idStatus)).build();
	}
	
	@GET
	@Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbsenceParIdUser(@PathParam("idUser") int idUser) {
		// le service retourne une ressoure : MaRessource et un code HTTP 200
		return Response.ok((Absence.getIdUser(idUser)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAbsence(Absence absence) {
		// le service cr�e une ressoure MaRessource, retourne l'ID de la ressource et un code HTTP 201
		Absence result = Absence.createAbsence(Absence);
		return Response.status(Status.CREATED).entity(result.getId()).build();
	}
	
	@PUT
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("id") int id) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(absence);
		return Response.ok(absence).build();
	}
	
	@PUT
    @Path("/{startDate}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("startDate") Date startDate) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(absence);
		return Response.ok(absence).build();
	}
	
	@PUT
    @Path("/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("endDate") Date endDate) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(absence);
		return Response.ok(absence).build();
	}
	
	@PUT
    @Path("/{reason}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("reason") String reason) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(absence);
		return Response.ok(absence).build();
	}

	@PUT
    @Path("/{idAbsenceType}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("idAbsenceType") int idAbsenceType) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(idAbsenceType);
		return Response.ok(absence).build();
	}
	
	@PUT
    @Path("/{idStatus}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("idStatus") int idStatus) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(idStatus);
		return Response.ok(idStatus).build();
	}
	
	@PUT
    @Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbsence(@PathParam("idUser") int idUser) {
		// le service met � jour une ressoure MaRessource, retourne la ressource et un code HTTP 200
		absence.updateAbsence(idUser);
		return Response.ok(idUser).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("id") int id) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(id);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{startDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("startDate") Date startDate) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(startDate);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("endDate") Date endDate) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(endDate);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{reason}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("reason") String reason) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(reason);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{idAbsenceType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("idAbsenceType") int idAbsenceType) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(idAbsenceType);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{idStatus}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("idStatus") int idStatus) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(idStatus);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAbsence(@PathParam("idUser") int idUser) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
                absence.deleteAbsence(idUser);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	
	
	
	Client client = ClientBuilder.newBuilder().newClient();
	WebTarget target = client.target("http://<host>/test/rest");
	target = target.path("ma-ressource/" + 1);
	 
	Invocation.Builder builder = target.request();
	Response response = builder.get();
	MaRessource maRessource = builder.get(Absence.class);

	
	
	
}