<%-- 
    Document   : index
    Created on : Nov 25, 2019, 9:45:23 PM
    Author     : Tara Ram Goyal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Upload to DB</title>
    </head>
    <body>
        <center>
            <h3> Image Upload to DB</h3>
            <form method="post" action="FileUpload" enctype="multipart/form-data">
                <label>Item Name</label>
                <input type="text" name="item_name" required/><br><br>
                <input type="file" name="filename" required/><br><br>
                <input type="submit" value="Upload"/><br><br>
                <a href="list">see list of all items</a>
            </form>
        </center>
    </body>
</html>
