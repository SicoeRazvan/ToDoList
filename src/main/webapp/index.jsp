<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if (o == null) {
        response.sendRedirect("login.html");
    }
%>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/css.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Yes_check.svg/600px-Yes_check.svg.png"
             width="50px" height="50px">
        <h1>To Do List</h1>
        <p><a href="logout">Logout</a></p>
    </div>

</header>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 ">
            <div class="todolist not-done">
                <h2>Todos</h2>
                <input type="text" class="form-control add-todo" id="task" placeholder="Add todo">
                <button id="checkAll" class="btn btn-success" onclick="addTask()">Add Task</button>

                <hr>
                <ul id="sortable" class="list-unstyled">
                    <li class="ui-state-default">

                    </li>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div id="doneTasks" class="todolist">
                <h1>Already Done</h1>
                <ul id="done-items" class="list-unstyled">

                </ul>
            </div>
        </div>
    </div>
</div>
<script src="js/index.js"></script>
</body>
