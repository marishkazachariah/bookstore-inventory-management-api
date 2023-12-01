package com.bookstore.resources;

import com.bookstore.model.Book;
import com.bookstore.service.BookServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    BookServiceDao bookServiceDao = new BookServiceDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        try {
            List<Book> books = bookServiceDao.getAllBooks();

            if (books != null && !books.isEmpty()) {
                return Response.ok(books).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No books found")
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
    public Response getBook(@PathParam("id") int id) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid book ID: " + id)
                    .build();
        }

        try {
            Book book = bookServiceDao.getBookById(id);

            if (book != null) {
                return Response.ok(book).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Book with ID " + id + " not found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    // Could not get this to work on time
    @GET
    @Path("/byAuthor/{authorName}")
    public Response getBooksByAuthor(@PathParam("authorName") String authorName) {
        try {
            List<Book> booksByAuthor = bookServiceDao.getBooksByAuthor(authorName);
            return Response.ok(booksByAuthor).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        try {
            bookServiceDao.addBook(book);

            return Response.status(Response.Status.CREATED)
                    .entity(book)
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
    public Response updateBook(@PathParam("id") int id, Book book) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid book ID: " + id)
                    .build();
        }

        try {
            book.setId(id);
            bookServiceDao.updateBook(book);

            return Response.ok(book).build();
        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        if(id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid book ID: " + id)
                    .build();
        }
        try {
            bookServiceDao.deleteBook(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }
}



