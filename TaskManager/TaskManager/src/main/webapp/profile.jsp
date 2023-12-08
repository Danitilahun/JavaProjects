<%@ page import="com.itsc.User" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>
    <%@ include file="header.jsp" %>
<body>
    <% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        Optional<User> optionalUser = Optional.ofNullable((User)request.getAttribute("user"));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
    %>
    <div class="content">
    <h1>User Profile</h1>
    <h1><%= user.getUsername()%></h1>
    
    <button class="create" onclick="openOverlay()">Edit Profile</button>

    <!-- Task Overlay -->
    <div id="overlay"  onclick="closeOverlay(event)">
        <form action="ProfileServlet" method="post">
        <label>Username: <input type="text" name="username" value="<%= user.getUsername() %>" required></label><br>
        <label>Password: <input type="password" name="password" value = "<%=user.getPassword() %>" required></label><br>
        <input type="hidden" name="userId" value="<%= user.getId() %>">
        <input type="submit" value="Update">
    </form>
    </div>
    </div>

    <script>
        function openOverlay() {
            
            document.getElementById("overlay").style.display = "flex";
        }
        function closeOverlay(event) {
            if (event.target === document.getElementById("overlay")) {
                document.getElementById("overlay").style.display = "none";
            }
        }
    </script>
    <% } else { %>
        <p>No user information available.</p>
    <% } %>
</body>
</html>
