<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        <form method="post" action="note">
        <p>Title: <input type="text" name="title" value="${note.title}"></p>
        <p>Contents: 
        <textarea cols="25" rows="10" name="content">${note.content}</textarea></p>
        <input type="submit" value="Save">
        </form>
    </body>
</html>
