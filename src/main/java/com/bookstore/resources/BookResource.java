package com.bookstore.resources;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.BookServiceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    BookService bookService = new BookServiceDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();

            if (books != null && !books.isEmpty()) {
                return Response.ok(books).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No books found")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving books from the database")
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
            Book book = bookService.getBookById(id);

            if (book != null) {
                return Response.ok(book).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Book with ID " + id + " not found")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving book from the database")
                    .build();
        }
    }

    @GET
    @Path("/byAuthor/{authorName}")
    public Response getBooksByAuthor(@PathParam("authorName") String authorName) {
        try {
            List<Book> booksByAuthor = bookService.getBooksByAuthor(authorName);
            return Response.ok(booksByAuthor).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving books by author from the database")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        try {
            bookService.addBook(book);

            return Response.status(Response.Status.CREATED)
                    .entity(book)
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding book to the database")
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
            bookService.updateBook(book);

            return Response.ok(book).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating book in the database")
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
            bookService.deleteBook(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting book from the database")
                    .build();
        }
    }
}
