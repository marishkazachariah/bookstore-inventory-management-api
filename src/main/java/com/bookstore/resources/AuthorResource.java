package com.bookstore.resources;

import com.bookstore.model.Author;
import com.bookstore.service.AuthorServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    AuthorServiceDao authorServiceDao = new AuthorServiceDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        try {
            List<Author> books = authorServiceDao.getAllAuthors();

            if (books != null && !books.isEmpty()) {
                return Response.ok(books).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No authors found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("id") int id) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid book ID: " + id)
                    .build();
        }

        try {
            Author author = authorServiceDao.getAuthorById(id);

            if (author != null) {
                return Response.ok(author).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Author with ID " + id + " not found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author) {
        try {
            authorServiceDao.addAuthor(author);

            return Response.status(Response.Status.CREATED)
                    .entity(author)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
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
            authorServiceDao.updateAuthor(author);

            return Response.ok(author).build();
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        if(id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid author ID: " + id)
                    .build();
        }
        try {
            authorServiceDao.deleteAuthor(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }
}



