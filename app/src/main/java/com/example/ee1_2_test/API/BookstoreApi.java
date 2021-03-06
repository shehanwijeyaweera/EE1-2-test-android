package com.example.ee1_2_test.API;

import com.example.ee1_2_test.JSON.Checkout;
import com.example.ee1_2_test.JSON.RequestLogin;
import com.example.ee1_2_test.Model.AddbookSpinnerData;
import com.example.ee1_2_test.Model.AdminDashboard;
import com.example.ee1_2_test.Model.AdminUserView;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.CartItem;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.Model.Refund;
import com.example.ee1_2_test.Model.RegisterResponse;
import com.example.ee1_2_test.Model.RequestBook;
import com.example.ee1_2_test.Model.RequestBook2;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.Model.loginResponse2;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface BookstoreApi {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{id}")
    Call<Book> getBookdetails(@Path("id") int bookid);

    @POST("login")
    Call<loginResponse2> getloginRespones(@Body RequestLogin requestLogin);

    @GET("admindashboard")
    Call<AdminDashboard> getDashboardstats();

    @GET("custorders")
    Call<List<Customer_orders>> getAllOrders();

    @GET("custpendingorders")
    Call<List<Customer_orders>> getAllPendingOrders();

    @GET("custshippedorders")
    Call<List<Customer_orders>> getAllShippedOrders();

    @GET("customerorders/{userid}")
    Call<List<Customer_orders>> getCustomerPastOrders(@Path("userid") Integer userID);

    @POST("checkout/{username}/{total}")
    Call<loginResponse> checkoutCart(@Path("username") String username, @Path("total") double total, @Body List<CartItem> cartItems);

    @GET("search/{keyword}")
    Call<List<Book>> getSearchResults(@Path("keyword")String keyword);

    @POST("newUser/register")
    Call<RegisterResponse> getRegisterResponse(@Body User user);

    @POST("bookRequest")
    Call<loginResponse> getBookRequestResponse(@Body RequestBook requestBook);

    @GET("user/refundReq/{user_id}")
    Call<List<Refund>> getUserRefundRequests(@Path("user_id")int user_id);

    @POST("user/refundReq/{order_id}/{user_id}")
    Call<loginResponse> postRefundReq(@Body Refund refund,@Path("order_id")String order_id,@Path("user_id") int user_id);

    @GET("user/getprofileInfo/{user_id}")
    Call<User> getprofileInfo(@Path("user_id")int id);

    @POST("user/updateprofile/{user_id}")
    Call<loginResponse> updateprofiledata(@Path("user_id")int id, @Body User user);

    @POST("admin/updatebook/save/{bookId}")
    Call<loginResponse> updateBookDetails(@Body Book book,@Path("bookId")int bookId);

    @GET("admin/categories")
    Call<List<Category>> getAllCategories();

    @GET("admin/authors")
    Call<List<Author>> getAllAuthors();

    @POST("admin/updateCategory/{categoryid}")
    Call<loginResponse> updateCategorydetails(@Body Category updatedCategory,@Path("categoryid") int id);

    @POST("admin/updateAuthor/{authorid}")
    Call<loginResponse> updateAuthorDetails(@Body Author updatedAuthor,@Path("authorid") int id);

    @GET("admin/deleteCategory/{categoryid}")
    Call<loginResponse> deleteCategory(@Path("categoryid") Integer categoryId);

    @GET("admin/deleteAuthor/{authorID}")
    Call<loginResponse> deleteAuthor(@Path("authorID") int id);

    @POST("admin/addnewCategory/save")
    Call<loginResponse> addnewCategory(@Body Category newCategory);

    @POST("admin/addnewAuthor/save")
    Call<loginResponse> addNewAuthor(@Body Author newAuthor);

    @GET("admin/getAllNonresponededRefundreq")
    Call<List<Refund>> getAllNonResponededRefundRequests();

    @GET("admin/getAllRefundedRefunds")
    Call<List<Refund>> getRefundedRefundRequests();

    @GET("admin/getAllRejectedRefunds")
    Call<List<Refund>> getRejectedRefundRequests();

    @GET("admin/rejectRequest/{refundId}")
    Call<loginResponse> rejectRefund(@Path("refundId") int id);

    @GET("admin/acceptRequest/{refundId}")
    Call<loginResponse> acceptRefund(@Path("refundId")int id);

    @GET("admin/getAllBookRequests")
    Call<List<RequestBook2>> getAllBookRequests();

    @GET("admin/addBook/getSpinnerData")
    Call<AddbookSpinnerData> getAddBookSpinnerData();

    @POST("admin/savebook/{categoryID}/{authorID}")
    Call<loginResponse> saveBookData(@Body Book book, @Path("categoryID")int categoryID, @Path("authorID")int authorID);

    @Multipart
    @POST("admin/savebookImage/{bookId}")
    Call<loginResponse> saveBookImage(@Part MultipartBody.Part file, @Path("bookId") int bookID);

    @GET("admin/getAllusers")
    Call<List<AdminUserView>> getAllUsersAdmin();

    @GET("admin/deleteUser/{userID}")
    Call<loginResponse> deleteUser(@Path("userID") int id);

    @GET("admin/markOrderAsShipped/{orderID}")
    Call<loginResponse> markOrderAsShipped(@Path("orderID") int id);
}
