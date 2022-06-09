<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <br>
        <h2>View Note</h2>
        <p><span style="font-weight: bold">Title: </span>${note.title}</p>
        <p><span style="font-weight: bold">Contents: </span><br>
        ${note.content}
        </p>
        <br>
        <a href="note?edit">Edit</a>
    </body>
</html>
