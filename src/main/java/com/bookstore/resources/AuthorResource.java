package com.bookstore.resources;

import com.bookstore.model.Author;
import com.bookstore.service.AuthorService;
import com.bookstore.service.AuthorServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    AuthorService authorService = new AuthorServiceDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthors() {
        try {
            List<Author> authors = authorService.getAllAuthors();

            if (authors != null && !authors.isEmpty()) {
                return Response.ok(authors).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No authors found")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving authors from the database")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("id") int id) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid author ID: " + id)
                    .build();
        }

        try {
            Author author = authorService.getAuthorById(id);

            if (author != null) {
                return Response.ok(author).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Author with ID " + id + " not found")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving author from the database")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author) {
        try {
            authorService.addAuthor(author);

            return Response.status(Response.Status.CREATED)
                    .entity(author)
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding author to the database")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid author ID: " + id)
                    .build();
        }

        try {
            author.setId(id);
            authorService.updateAuthor(author);

            return Response.ok(author).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating author in the database")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid author ID: " + id)
                    .build();
        }
        try {
            authorService.deleteAuthor(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting author from the database")
                    .build();
        }
    }
}
